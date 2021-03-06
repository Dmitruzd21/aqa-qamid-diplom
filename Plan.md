# **План тестирования веб-сервиса покупки тура**

## **Перечень автоматизируемых сценариев при покупке тура по дебетовой карте**

**Позитивные сценарии:**

**1. Успешная покупка тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Появление информационного окна с текстом: "Успешно. Операция одобрена Банком"

**2. Ввод 18 цифр поле номера карты в форме для покупки тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести 18 значный номер: 1111 2222 3333 4444 55 - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Появление информационного окна с текстом: "Успешно. Операция одобрена Банком"

**3. Ввод 19 цифр поле номера карты в форме для покупки тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести 19 значный номер: 1111 2222 3333 4444 555 - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Появление информационного окна с текстом: "Успешно. Операция одобрена Банком"

**Негативные сценарии:**

**Поле номера карты**

**1. Пустое поле номера карты в форме для покупки тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- Поле номер карты оставить пустым - появление сообщения возле поля "Неверный формат"
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Информационного окно с текстом: "Неверный формат". Отправка формы не происходит.

**2. Ввод латинских букв в поле номера карты в форме для покупки тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести: dddd vvvv nnnn xxxx - ввод невозможен (поле остается пустым)
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Ввод невозможен (поле остается пустым). Появление сообщения об ошибке. Отправка формы НЕ происходит.

**3. Ввод кириллических букв в поле номера карты в форме для покупки тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести: кккк ррррр нннн оооо - ввод невозможен (поле остается пустым)
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Ввод невозможен (поле остается пустым). Появление сообщения об ошибке. Отправка формы НЕ происходит.

**4. Ввод символов в поле номера карты в форме для покупки тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести: %%%% ???? ;;;; """" - ввод невозможен (поле остается пустым)
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Ввод невозможен (поле остается пустым). Появляется сообщение об ошибке. Отправка формы НЕ происходит.

**5. Отклонение платежа при вводе номера DECLINED карты**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер: 5555 6666 7777 8888 - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Появление информационного окна с текстом: "Ошибка! Банк отказал в проведении операции."

**Поле "Месяц"**

**1. Ввод месяца в ином формате в форме для покупки тура по дебетовой карте**

Precondition: 

открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер карты из предусловия - успешный ввод
- В поле месяц ввести: 2 - появление сообщения возле поля месяца: "Неверный формат"
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Появление ошибки: "Неверный формат". Отправка формы НЕ происходит.

**2. Ввод несуществующего месяца (13) в форме для покупки тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 13 - появление сообщения возле поля месяца: "Неверно указан срок действия карты"
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Появление сообщения возле поля месяца: "Неверно указан срок действия карты". Отправка формы НЕ происходит.

**3. Ввод несуществующего месяца (0) в форме для покупки тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 0 - появление сообщения возле поля месяца: "Неверный формат"
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Появление сообщения возле поля месяца: "Неверный формат". Отправка формы НЕ происходит.

**4. Пустое поле мясяца в форме для покупки тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- Поле месяц ввести: оставить пустым - появление сообщения возле поля месяца: "Неверный формат"
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Появление сообщения возле поля месяца: "Неверный формат". Отправка формы НЕ происходит.

**5. Ввод латинских букв в поле месяц в форме для покупки тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: sd - ввод невозможен (поле остается пустым)
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Ввод невозможен (поле остается пустым). Появление сообщения об ошибке. Отправка формы НЕ происходит.

**6. Ввод кириллических букв в поле месяц в форме для покупки тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: ва - ввод невозможен (поле остается пустым)
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Ввод невозможен (поле остается пустым). Появление сообщения об ошибке. Отправка формы НЕ происходит.

**7. Ввод символов в поле месяц в форме для покупки тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: %% - ввод невозможен (поле остается пустым)
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Ввод невозможен (поле остается пустым). Появление сообщения об ошибке. Отправка формы НЕ происходит.

**Поле "Год"**

**1. Ввод года карты больше текущего на 5 лет в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 27 - появление сообщения возле поля года "Неверно указан срок действия карты"
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Появление сообщения возле поля года "Неверно указан срок действия карты". Отправка формы НЕ происходит.  

**2. Ввод прошлого года в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 20 - появление сообщения возле поля года "Истёк срок действия карты"
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Появление сообщения возле поля года "Истёк срок действия карты". Отправка формы НЕ происходит.

**3. Пустое поля года в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- Поле год оставить пустым - появление сообщения возле поля года "Неверный формат"
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Появление сообщения возле поля года "Неверный формат". Отправка формы НЕ происходит.

**4. Ввод латинских букв в поле год в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: ff - ввод невозможен
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Ввод невозможен. Появление сообщения об ошибке. Отправка формы НЕ происходит.

**5. Ввод кириллических букв в поле год в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: нн - ввод невозможен (поле остается пустым)
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Ввод невозможен (поле остается пустым). Появление сообщения об ошибке. Отправка формы НЕ происходит.

**6. Ввод смиволов в поле год в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: %$ - ввод невозможен (поле остается пустым)
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Ввод невозможен (поле остается пустым). Появление сообщения об ошибке. Отправка формы НЕ происходит.

**Поле "Владелец"**

**1. Ввод фамилии и имени на кириллице в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Иванов Иван - появление сообщения возле поля Владелец "Неверно введено имя владельца"
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Информационное окно с текстом: "Допустимо использовать только латинские буквы". Отправка формы НЕ происходит.

**2. Ввод символов в поле владелец в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: ;:*?% - появление сообщения возле поля Владелец "Неверный формат"
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Информационное окно с текстом: "Неверный формат". Отправка формы НЕ происходит.

**3. Пустое поле владельца в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец оставить пустым - появление сообщения возле поля Владелец "Поле обязательно для заполнения"
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Появление сообщения возле поля Владелец "Поле обязательно для заполнения". Отправка формы НЕ происходит.

**4. Ввод цифр в поле владелец в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: 5347457 - появление сообщения возле поля Владелец "Неверный формат"
- В поле CVC/CVV ввести: 537 - успешный ввод
- Кликнуть кнопку "Продолжить"

**ОР:** Информационное окно с текстом "Неверный формат". Отправка формы НЕ происходит.

**Поле "CVC/CVV"**

**1. Ввод двузначного кода CVC в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivanov Ivan - успешный ввод
- В поле CVC/CVV ввести: 22 - появление сообщения "Неверный формат"
- Кликнуть кнопку "Продолжить"

**ОР:** Появление сообщения "Неверный формат". Отправка формы НЕ происходит.

**2. Ввод однозначного кода CVC в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivanov Ivan - успешный ввод
- В поле CVC/CVV ввести: 2 - появление сообщения "Неверный формат"
- Кликнуть кнопку "Продолжить"

**ОР:** Появление сообщения "Неверный формат". Отправка формы НЕ происходит.

**3. Ввод четырехзначного кода CVC в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле CVC/CVV ввести: 5678 - ввод невозможен (поле остается пустым)

**ОР:** Ввод невозможен (поле остается пустым). Появление сообщения об ошибке.

**4. Ввод латинских букв в поле CVC в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: ddd - ввод невозможен (поле остается пустым)
- Кликнуть кнопку "Продолжить"

**ОР:** Ввод невозможен (поле остается пустым). Появление сообшения об ошибке. Отправка формы НЕ происходит.

**5. Ввод кириллических букв в поле CVC в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: ррр - ввод невозможен (поле остается пустым)
- Кликнуть кнопку "Продолжить"

**ОР:** Ввод невозможен (поле остается пустым). Появления сообщения об ошибке. Отправка формы НЕ происходит.

**6. Ввод символов в поле CVC в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivan Ivanov - успешный ввод
- В поле CVC/CVV ввести: #@% - ввод невозможен (поле остается пустым)
- Кликнуть кнопку "Продолжить"

**ОР:** Ввод невозможен (поле остается пустым). Появление сообщения об ошибке. Отправка формы НЕ происходит.

**7. Пустое поле кода CVC в форме для покупке тура по дебетовой карте**

Precondition: открыть веб-сервис по ссылке: http://localhost/8080/

Валидный номер карты: 1111 2222 3333 4444

Steps to repoduce:

- Кликнуть кнопку "Купить" - появление формы для введения банковских данных ("Оплата по карте")
- В поле номер карты ввести валидный номер из предусловия - успешный ввод
- В поле месяц ввести: 02 - успешный ввод
- В поле год ввести: 23 - успешный ввод
- В поле владелец ввести: Ivanov Ivan - успешный ввод
- В поле CVC/CVV оставить пустым - появление сообщения "Неверный формат"
- Кликнуть кнопку "Продолжить"

**ОР:** Появление сообщения "Неверный формат". Отправка формы НЕ происходит.

## **Перечень автоматизируемых сценариев при покупке тура через кредит**

ПРИМЕЧАНИЕ: Все сценарии аналогичны предыдущим с тем исключением, что первый шаг каждого сценария - клип по кнопке "Купить в кредит"

**ТЕСТИРОВАНИЕ ИНТЕГРАЦИИ С БАЗОЙ ДАННЫХ**

**Позитивные сценарии:**

1. SELECT запрос статуса покупки после отправки формы с валидными данными (валидный номер карты)

**ОР:** статус "APPROVED" после отправления форрмы с валидными данными (валидный номер банковской карты)

2. SELECT запросы о количестве записей в таблице до совершения покупки и после

**ОР:** количество записей после покупки на единицу больше, чем до совершения покупки

**Негативные сценарии**

1. SELECT запросы о количестве записей в таблице до совершения покупки и после при заполнении формы различными невалидными данными

**ОР:** количество записей после покупки равно количеству записей до покупки

2. SELECT запрос статуса покупки после отправки формы с DECLINED карты

**ОР:** статус "DECLINED" после отправления форрмы с данными DECLINED карты

**ТЕСТИРОВАНИЕ API**

**Позитивные сценарии:**

1. POST запрос с валидными данными в теле запроса

**ОР:** Status code:200. Cтатус: APPROVED

2. POST запрос с номером заблокированной карты в теле запроса

**ОР:** Status code:200. Cтатус: DECLINED

**Негативные сценарии**

2. POST запрос с невалидными данными в теле запроса

**ОР:** Status code: отличается от 200

## **Перечень используемых инструментов с обоснованием выбора**

**Gradle** (наиболее современный фреймворк, более простая интеграция с другими инструментами)

**JUnit5** (наиболее современная библиотека тестирования)

**Selenide** (удобство для тестирования в разных браузерах, упрощенный синтаксис, более простая работа с селекторами - поиск по тексту)

**Lombok** (позволяет при помощи аннотаций составлять конструкторы, геттеры и сеттеры)

**Docker** для быстрой загрузки и развертывания контейнера с MySQL

**MySQL** (более простое использование)

**Allure** для составления отчетов (более наглядно и систематизировано представлены результаты тестирования)

**CI: Java for Gradle** простая интеграция с git-hub

## **Перечень и описание возможных рисков при автоматизации**

- Риск того, что автоматизация такого небольшого проекта не окупится. Гораздо проще провести мануальное тестирование.
- Сложно поддерживать автотесты: возможно изменение названия полей, изменение текста предупреждений об ошибке

## **Перечень необходимых разрешений/данных/доступов**

1. Номер тестовы карты

2. Тестовый режим сервиса (желательно)

3. Предоставление разрешения к БД

## **Перечень необходимых специалистов для автоматизации**

Инженер по автоматизации тестирования - 1

## **Интервальная оценка с учётом рисков (в часах)**

Подготовка проекта (настойка окуржения) - 3 часа

Подготовка мануальных тестов (10 часов)

Автоматизация мануальных тестов (16 часов)

Проведение тестирования (2 часа)

Формирование отчета о тестировании (2 часа)

## **План сдачи работ (когда будут автотесты, результаты их прогона и отчёт по автоматизации)**

Составление плана тестирования (19.12.21-20.12.21)

Подготовка мануальных тестов (19.12.21-20.12.21)

Автоматизация мануальных тестов (21.12.21-27.12.21)

Проведение тестирования (28.12.21-29.12.21)

Формирование отчета о тестировании (29.12.21)