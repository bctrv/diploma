## Документация
[План автоматизации тестирования](https://github.com/bctrv/diploma/blob/main/docs/Plan.md)

[Отчет по итогам тестирования](https://github.com/bctrv/diploma/blob/main/docs/Report.md)

[Отчёт по итогам автоматизации](https://github.com/bctrv/diploma/blob/main/docs/Summary.md)


## Процеруда запуска автотестов:

1. Открыть Docker, где загружены контейнеры Node, MySQL и PostgreSQL.
2. В терминале IntelliJ IDEA запустить контейнеры командой `docker-compose up`
3. В терминале запустить приложение:
   для базы MySQL командой `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar`
   для базы PostgreSQL командой  `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar`
4. Запустить тесты:
   для базы MySQL командой `./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"`
   для базы PostgreSQL командой  `./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`
5. Запустить отчет командой `./gradlew allureServe`
