package online.tatarintsev.liblessons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import online.tatarintsev.liblessons.databinding.ActivityMainBinding
import online.tatarintsev.liblessons.presenter.MainPresenter
import online.tatarintsev.liblessons.view.MainActivityState
import online.tatarintsev.liblessons.view.MainView

class MainActivity: AppCompatActivity(), MainView {

    private var currencyActivityState: MainActivityState = MainActivityState

    private lateinit var presenter: MainPresenter

    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        // активити получает презентер из внешнего класса
        // он лениво создается при первом обращении
        // если активити будет пересоздано, а процесс приложения не будет убит,
        // при следующем обращении вернётся уже созданный презентер,
        // в котором можно хранить необходимые данные
        presenter = currencyActivityState.getPresenter()
        presenter.onCreate(savedInstanceState)


        val listener = View.OnClickListener {
            val count = when(it.id) {
                vb?.btnCounter1?.id -> 0
                vb?.btnCounter2?.id -> 1
                vb?.btnCounter3?.id -> 2
                else -> { throw Exception("Unknow element")}
            }
            // не по идеологии модели, ввожу метод updaeCounter
            //(it as Button).text = presenter.onUserAction(count).toString()
            presenter.onUserAction(count)
        }

        vb?.btnCounter1?.setOnClickListener(listener)
        vb?.btnCounter2?.setOnClickListener(listener)
        vb?.btnCounter3?.setOnClickListener(listener)
    }

    /**
     * метод устанавливат значени для каждого счетчика в связанном элементе
     * либо выбрасывает исключение
     * @param number - номер счетчика
     * @param value - новое значение счтчика
     */
    override fun updateCounter(number: Int, value: Int) {
        when(number) {
            0 -> vb?.btnCounter1?.text = value.toString()
            1 -> vb?.btnCounter2?.text = value.toString()
            2 -> vb?.btnCounter3?.text = value.toString()
            else -> throw Exception("Unknown element")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // одним из вариантов сохранения данных, переживающих уничтожение процесса
        // является использование Bundle. Это не очень чисто архитектурно, но можно допустить в качестве исключения
        presenter.onSaveInstanceState(outState)

        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()

        // в этом методе активити будет присоединено к презентеру
        currencyActivityState.onStart(this)
    }

    override fun onStop() {
        // в этом методе активити будет отсоединено от презентера, чтобы не допускать утечек памяти
        currencyActivityState.onStop()
        super.onStop()
    }

    @Override
    override fun onDestroy() {
        // isFinishing означает, что пользователь явно уходит из активити
        if (isFinishing) {
            // презентер больше не нужно хранить, ссылка на него будет обнулена
            currencyActivityState.onDestroy()
        }

        super.onDestroy()
    }

}