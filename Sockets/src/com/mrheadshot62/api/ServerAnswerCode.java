package com.mrheadshot62.api;

/**
 * Created by DmitriyRoot on 07.01.2017.
 */
public class ServerAnswerCode {

    //информационные
    public static final int SUCCESS= 3;                         // Запрос успешно выполнен
    public static final int PROCESSING = 100;                   // Запрос выполняется
    public static final int FOUND = 103;                        // Информация найдена
    public static final int VERSIONCLIENTNOTSUPPORTED = 105;    // Версия клиента устарела
    //ошибки клиента
    public static final int BADREQUEST = 400;                   // Неверный запрос
    public static final int UNAUTHRIZED = 401;                  // Не авторизирован
    public static final int NOCONTENT = 402;                    // Запрос пустой
    public static final int FORBIDDEN = 403;                    // Запрещено
    public static final int NOTFOUND = 404;                     // Не найденео
    public static final int PACKETNOTALLOWED = 405;             // Пакет не разрешён на сервере
    public static final int NOTACCEPTABLE = 406;                // Неприемлимый пакет
    public static final int NOTAUTHENTICATION = 407;            // Не авторизирован
    public static final int TIMEOUT = 408;                      // Ошибка времени отправки пакета
    public static final int CONFLICT = 409;                     // Конфликт пакетов на сервере
    public static final int GONE = 410;                         // Пакет просто удалён
    public static final int REQUESTLARGE = 413;                 // Запрос слишком большой
    public static final int UNSUPPORTEDTYPE = 415;              // Тип пакета не поддерживается на сервере
    public static final int UNSUPPORTEDIMAGETYPE = 416;         // Ваше изображение не поддерживается на сервеер
    public static final int LOCKED = 423;                       // Ваш запрос заблокирован (Добавить в проверку)
    public static final int TOOMANYREQUEST = 429;               // Слишком много запросов к серверу
    //ошибки сервера
    public static final int SERVERERROR = 500;                  // Ошибка сервера
    public static final int BADCONNECT = 502;                   // Плохое соеденение с сервером
    public static final int SERVICEUNAVIABLE = 503;             // Сервис недоступен
    public static final int REQUESTTIMEOUT = 504;               // Сервер выполнял запрос слишком долго
}
