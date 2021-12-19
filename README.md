## **CI - status:** [![Java CI with Gradle](https://github.com/Dmitruzd21/aqa-qamid-diplom/actions/workflows/gradle.yml/badge.svg)](https://github.com/Dmitruzd21/aqa-qamid-diplom/actions/workflows/gradle.yml)

## **Инструкция для запуска автотестов**

На вашем ПК должен быть установлени следующий soft:

- IntelliJ IDEA
- Docker desktop (совместно с Docker-compose)
- Chrome браузер

1. Создать git-репозиторий в заранее созданной папке: `git init`

2. Клонировать проект в репозиторий: 
   
`git cline https://github.com/Dmitruzd21/aqa-qamid-diplom`

3. Открыть проект в IntelliJ IDEA

4. Через терминал IDEA запустить контейнер с СУБД при помощи команды: `docker-compose up`

5. Через терминал IDEA запустить веб-сервис при помощи команды: `java -jar artifacts/aqa-shop.jar`

6. Проверить, что приложение успешно запустилось: для этого в Сhrome браузере введите следующий URL: http://localhost/8080/

7. Запустить автотесты через терминал IDEA при помощи команды: `gradlew test`
