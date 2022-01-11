<h1>Инструкция для настройки и запуска проекта</h1>
<hr>
<h2>Для запуска проекта требуется:</h2> 

- Java 17
- Maven 3.8.3 или выше
- БД Postgres 14 или выше
- Скачать UI составляющую проекта -  <a href = "https://github.com/Freeman2021/converterUI">ConverterUI</a>
<hr>

<h3>Инструкция по запуску:</h3>

<h4>Прописать в application.properties данные подключаемой базы данных:</h4>
Путь где лежит application.properties - \converter\src\main\resources\application.properties

- database.host - хост БД
- database.port - порт БД
- database.name - наименование БД
- database.scheme - схема БД
- spring.datasource.username - логин БД
- spring.datasource.password - пароль БД

<h4>В дальнейшем запустить консоль в корне проекта (\converter) и прописать команды:</h4>

- mvn package (Для того чтобы собрать проект)
- mvn spring-boot:run (Для запуска проекта)
- Открыть <a href = "https://github.com/Freeman2021/converterUI/blob/master/README.md">README файл проекта converterUI</a> и выполнить все инструкции там 
<hr>

<h1>Дополнительная информация</h1>
<h4>Скрипты для создания таблиц в БД лежат \converter\src\main\resources\db\migration</h4>
<h4>Для авторизации воспользоваться ЛОГИН - admin, ПАРОЛЬ - 123</h4>
<h4>Для изменения логина и пароля пользователя поменяйте значения LOGIN и PASSWORD в классе UserData.java (\converter\src\main\java\ru\smartsoft\converter\security\UserData.java)</h4>