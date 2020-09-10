Тестовое задание MeshGroup

В качестве тестового задания необходимо реализовать REST  приложение.












При разработке использовать Spring Boot. Остальное на ваше усмотрение. Желательно придерживаться экосистемы Spring.
При разработке использовать в качестве базы Postgres.
Cross-origin должно быть отключено.	
Доступ к сервису возможен только при наличии токена ‘secret’. Во всех остальных случаях, кроме “GET /exit”, возвращать 401. Название и реализация на ваше усмотрение. Инструкция для передачи токена должна прилагаться вместе с тестовым заданием.
Сервис должен запускатся на 8010 порту.
name и email должны быть регистронезависимые.
Добавить фильтр при регистрации на проверку уникальности поля email. В случае, если email есть в базе возвращать 403 статус.
К исходникам должен прилагаться собранный артефакт приложения.
Для данного приложения реализуйте и подключите OpenApi (Swagger)
Версия java не выше 11.
Сборщик Maven.
















endpoints:

POST /profiles/set 
Создает запись профиля и присваивает ему id
Request:
принимает json следующей структурой:

{
	“name”: string
	“email”: string
	“age”: int
}

Responses:
в случае успеха возвращает id записи пользователя

status 200
{
	“idUser”: int
}

В случае некорректного email
status 400

{
	“msg”: string
}

В случае если email уже передавался (реализовать через фильтр)
status 403

{
	“msg”: string
}

GET /profiles/last
Возвращает последний созданный профиль

Responses:
status 200
{
	“id”: int
	“name”: string
	“email”: string
	“age”: int
“created”: timestamp
}



GET /profiles
Возвращает все созданные профили

Responses:
status 200
[{
	“id”: int
	“name”: string
	“email”: string
	“age”: int
“created”: timestamp
}...]

GET /profiles/{ID}
Возвращает профиль по его ID

Responses:
status 200
{
	“id”: int
	“name”: string
	“email”: string
	“age”: int
“created”: timestamp
}

status 404 
в случае если запись не найдена
{
	“msg”: string
}





POST /profiles/get
Возвращает профиль по email

Request:
принимает json следующей структурой:

{
	“email”: string
}

Responses:
status 200
{
	“id”: int
	“name”: string
	“email”: string
	“age”: int
“created”: timestamp
}

status 404 
в случае если запись не найдена

{
	“msg”: string
}

GET /error/last
Возвращает сообщение последней ошибки

Responses:
status 200
{
	“msg”: string
	“created”: timestamp
}


Не обязательная часть задания:

GET /exit
Производит закрытия приложение с редиректом на страницу /exit-success (название вариативно) с надписью ‘приложение закрыто’ допускаются и другие варианты информирования о закрытие.








На этом все! Удачного прохождения теста.
