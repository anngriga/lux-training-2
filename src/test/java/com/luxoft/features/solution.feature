# language: ru

Функция: Тесты luxoft-training.ru

    @ex1
    Сценарий: Отображаются нужные кнопки на странице Расписание
      Пусть открыта домашняя веб-страница Luxoft-Training
      Когда выполнен переход на страницу Расписание
      То отображаются кнопки
        | Расписание |
        | Бесплатные семинары |
        | Онлайн-курсы |

    @ex2
    Сценарий: Отображаются нужные города на вкладке Контакты
      Пусть открыта домашняя веб-страница Luxoft-Training
      Когда выполнен переход на страницу Контакты
      То отображаются кнопки
        | Электроугли |
        | Санкт-Петербург |
        | Омск |
      Но не отображаются кнопки
        | Екатеринбург |

    @ex3
    Структура сценария: Пользователь может найти курсы в Каталоге
      Пусть открыта домашняя веб-страница Luxoft-Training
      Когда выполнен переход на страницу Каталог
      То доступна строка поиска курсов
      И доступно описание курса по идентификатору <id-курса>
    Примеры:
      | id-курса |
      | SQA-050  |
      | SQA-051  |