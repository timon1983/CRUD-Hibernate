                                                                                                   [![Build Status](https://travis-ci.com/timon1983/CRUD-Hibernate.svg)](https://travis-ci.com/timon1983/CRUD-Hibernate)

# CRUD-Hibernate.
1.Открыть проект в IntelliJ IDEA.
2.Запустить класс Main .
3.Работать с приложением посредством ввода команд ввиде числовых значений:
   1 - User
   2 - Post
   3 - Region
   4 - Exit
4.Работа с консолью реализована в каласса пакета view , которые реализуют интерфейс View c единственным методом
doChoise().
5.Обработка запросов пользователя производится классами в пекете controllers.
6.Обработанный запрос передается классам в пакете service , где формируется данные для запроса в классы пакета repository.
7.В пакете repository реализуются методы для непосредственного взаимодействия с БД PostgerSQL.
8.Слой service покрыт тестами , проверяющие коректность работы методов(возвращаемые данные, запуск методов),
с помощью JUnit и Mockito.
9.Миграция БД реализoвана с помощью flyway.
10.Взаимодействие с БД осуществляется с помощью ORM Hibernate.
