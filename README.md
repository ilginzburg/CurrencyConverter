
Android приложение для просмотра курса валют с сайта ЦБ <br />
https://www.cbr-xml-daily.ru/daily_json.js <br />
с возможностью конвертации заданной суммы в рублях в выбранную валюту. <br />
 <br />
 <br />
--Техническая реализация <br />
Приложение реализовано в архитектурной модели MVVM на языке Kotlin. <br />
Внедрение зависимостей реализовано с Hilt. <br />
Взаимодействие с сервером осуществляется с помощью OkHttp, Gson и Retrofit. <br />
Необходимые для отображения параметры (название валюты, номинал, курс и т.п) <br />
извлекаются из полученного JSON объекта <br />
в List<Coin> и сохраняются в локальном репозитории Room. <br />
LiveData Observer реагирует на обновления БД и обновляет данные в адаптере CoinListRecyclerAdapter. <br />
Данные отображаются в виде списка посредством RecyclerView. <br />
Время обновления базы данных сохраняется в SharedPrefernces.  <br /> 
При запуске приложения оно считывается и сравнивается с текущим.  <br />
При разнице 10 минут и менее отображаются данные из БД.  <br />
При разнице более 10 минут производится запрос в сеть и полная перезагрузка данных,
при этом старые данные удаляются из БД.  <br />
Асинхронность запросов по сети обеспечивают Coroutines. <br />
   <br />
   <br />
--Пользовательский интерфейс <br />
Информация о каждом виде валюты отображается в отдельном поле (использован CardView). <br />
Поля расположены вертикально относительно друг друга, без интервалов, с возможностью прокрутки. <br />
Для каждого вида валюты отображаются следующие параметры: код, название, номинал и курс в рублях. <br />
Если курс понизился, он отображается красным цветом, если поднялся или не изменился - зелёным.
При клике на определённую валюту появляется поле для ввода суммы в рублях. <br />
При клике на появившемся поле появляется числовая клавиатура. <br />
При вводе суммы появляется поле с отображением суммы в данной валюте, изменяющейся при каждом вводе. <br />
Эти поля исчезают при повторном клике по карточке валюты или при клике по другой карточке. <br />
Перезагрузка списка валют осуществляется посредством RefreshOnSwipe. <br />

