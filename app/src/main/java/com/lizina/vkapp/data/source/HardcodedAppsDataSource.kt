package com.lizina.vkapp.data.source

import com.lizina.vkapp.data.dto.AppDetailsDto
import com.lizina.vkapp.data.dto.SimilarAppDto


class HardcodedAppsDataSource {
    val apps: List<AppDetailsDto> = listOf(
        AppDetailsDto(
            id = "a7f3c9d2-5678-4abc-8823-91b4c8d5e6f7",
            name = "Календарь",
            developer = "Productivity Team",
            category = "PRODUCTIVITY",
            ageRating = 3,
            size = 12.5f,
            iconUrl = "https://static.rustore.ru/imgproxy/5sBMIYzbT5DMeNed7qy0BdEbRxgXf6Xya2gRIHWlr24/preset:web_app_icon_160/plain/https://static.rustore.ru/apk/2063634664/content/ICON/559fdb6b-69bc-4340-a9b7-4bf4a467f84b.jpg@webp",
            screenshotUrlList = listOf(
                "https://static.rustore.ru/imgproxy/nc8OVoaaa5mq7qT3Es5cre90yqP-SZUUyogkMqQGIOk/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/12/17/f2/apk/2063634664/content/SCREENSHOT/085bb269-b5da-4b2c-abbc-0c7fb874e208.jpg@webp",
                "https://static.rustore.ru/imgproxy/N4rw4pU4TUpOsmcupsMDg_tqfcdpbcKIsTIO7iwFF6I/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/2063634664/content/SCREENSHOT/6b9ad18e-a374-43aa-be96-af93441d4659.jpg@webp",
                "https://static.rustore.ru/imgproxy/mv3FgD8MmP6iJymwu4GOTGPTjx-pI0Hl6uZbWsxfT5k/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/2063634664/content/SCREENSHOT/10b2e6fa-c040-4e3e-a507-07b54415b824.jpg@webp"
            ),
            description = "Планируйте события и встречи, управляйте расписанием."
        ),
        AppDetailsDto(
            id = "b1e4a8c3-9012-4def-7745-32f9a1b8c7d6",
            name = "Погода",
            developer = "Utilities Team",
            category = "UTILITIES",
            ageRating = 3,
            size = 8.2f,
            iconUrl = "https://static.rustore.ru/imgproxy/g_FEnTsf6hvUd49Q4-7P4wj-cvdBE-40V2zNVpQPz5E/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/937663/content/ICON/4984fd0f-f97c-4e73-85e5-474880cce068.png@webp",
            screenshotUrlList = listOf(
                "https://static.rustore.ru/imgproxy/0A8_oixnJofAo_60pmqp4LqPeWVwCTWYJBMADFIb5k4/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/2/3/86/apk/937663/content/SCREENSHOT/fb182c35-0f45-47a4-bda8-74d512e8d5bb.png@webp",
                "https://static.rustore.ru/imgproxy/OINr_ezpIq97TvjgUmj2z1TBZ-NdG_UurMD_RfaC7oo/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/2/4/ec/apk/937663/content/SCREENSHOT/bd14f5eb-dbb0-4eb7-a1ab-2b3f7432ad28.png@webp",
                "https://static.rustore.ru/imgproxy/Qbv53_EafnrvXOefOCBNDI-xnjjUY4cVBy0sCb4raOM/preset:web_scr_prt_162/plain/https://static.rustore.ru/701d5a12-510f-4c02-97c2-9c29a1bf1af1@webp",
                "https://static.rustore.ru/imgproxy/UFsWIQFGY8vG3_wkqYkmTANG42se3jOmd2TjSBP0_NU/preset:web_scr_prt_162/plain/https://static.rustore.ru/87b0a8f6-a61a-4ba4-a777-a5bded5c166d@webp",
                "https://static.rustore.ru/imgproxy/YxCdZcJeIX-UG5xWNqQPJ2K_0NCg8JLUXTMXEesXjmI/preset:web_scr_prt_384/plain/https://static.rustore.ru/2026/2/3/ff/apk/937663/content/SCREENSHOT/d00f5f2f-dcfd-4164-96f2-51146df67375.png@webp"
            ),
            description = "Прогноз погоды на сегодня и ближайшие дни с картой осадков."
        ),
        AppDetailsDto(
            id = "c5d7b9a4-3456-4ghi-6612-83e2f1a9b4c5",
            name = "Заметки",
            developer = "Notes Team",
            category = "PRODUCTIVITY",
            ageRating = 3,
            size = 9.1f,
            iconUrl = "https://static.rustore.ru/imgproxy/MaLKt7hxHs_CDTqPZnmSHKbynUob_IoQWDVXuhrBtnQ/preset:web_app_icon_62/plain/https://static.rustore.ru/2026/1/10/a9/apk/2063687627/content/ICON/502d7f98-0df3-446b-94f3-a5c9947c34e4.png@webp",
            screenshotUrlList = listOf(
                "https://static.rustore.ru/imgproxy/VFDKReUw_Gix52RW3oFCq4Mbz9F-Zen7MoULh8zEwxY/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/1/10/d4/apk/2063687627/content/SCREENSHOT/db2fd432-0bdc-4afd-9466-7ab2dfa36933.png@webp",
                "https://static.rustore.ru/imgproxy/hsLh8tX27DAlXZmSkLT3wKZk303fzTv4rR2sxri0OKE/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/1/10/7f/apk/2063687627/content/SCREENSHOT/6dc5d0f2-b126-4d23-bba9-4114fedc401d.png@webp",
                "https://static.rustore.ru/imgproxy/LZnZhFttSkVwYKrRarPhirzhAc00pEdGTp20_XAmJyU/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/1/10/01/apk/2063687627/content/SCREENSHOT/a2efa969-8d96-407d-a292-ef3af3d96bf3.png@webp",
                "https://static.rustore.ru/imgproxy/ii6X5WiS8gRKUkkfuUcGdUE9UP4vDMoSd9FgEXwfo1Y/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/1/10/d2/apk/2063687627/content/SCREENSHOT/62cfb0e9-34d1-4ea2-8e9d-50566d16a77b.png@webp",
                "https://static.rustore.ru/imgproxy/lKvYF_wYCkOuvwUZJnkQGHP9G82gWQ3rIB5KmMop94Q/preset:web_scr_prt_384/plain/https://static.rustore.ru/2026/1/10/08/apk/2063687627/content/SCREENSHOT/7e617d6c-4c59-4e15-99f8-b9e0b439697d.png@webp"
            ),
            description = "Создавайте быстрые заметки, списки и напоминания."
        ),
        AppDetailsDto(
            id = "d8e9c7b6-7890-4jkl-5534-21d3c4b5a6e7",
            name = "Галерея",
            developer = "Photo Team",
            category = "PHOTOGRAPHY",
            ageRating = 3,
            size = 23.4f,
            iconUrl = "https://static.rustore.ru/imgproxy/bZYtvPq5XBF_SZNc7MzE-7JoaIX32t5pnjMzLkAgqEI/preset:web_app_icon_62/plain/https://static.rustore.ru/2025/10/29/4e/apk/1504831423/content/ICON/52864908-1488-4b5d-b9c0-50b042db09c6.png@webp",
            screenshotUrlList = listOf(
                "https://static.rustore.ru/imgproxy/VYu4msU6B3SMqIqbbm2DxBm1jxBIR78MMxZU2VxygEI/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/1504831423/content/SCREENSHOT/ab518362-967c-4d8c-92bb-99f628e8e76b.png@webp",
                "https://static.rustore.ru/imgproxy/d_6SaCEb-E8oyouBt5HB3uA1Un0syOtKeOH8wwhMrq4/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/1504831423/content/SCREENSHOT/b66f1971-f506-48c8-95ca-138b24f87de1.png@webp",
                "https://static.rustore.ru/imgproxy/SiKBf-f_wTDOYdrYCoJs8bY7sKqgoEEdZvEgx1K_Mgg/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/1504831423/content/SCREENSHOT/f8e9b345-9f23-4c9d-9fb7-2c31fd0e3d0c.png@webp",
                "https://static.rustore.ru/imgproxy/pBe6J837qE9G6iimaZhnIz8nIm5C1CFP1AhynyGiBdY/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/1504831423/content/SCREENSHOT/ea3645d4-b33d-4b68-8b91-13a1d024c347.png@webp",
                "https://static.rustore.ru/imgproxy/L68SPKsXZRKmH2FlAoxhzikkuIuGOhyQ2zVXbgQg2Gk/preset:web_scr_prt_384/plain/https://static.rustore.ru/apk/1504831423/content/SCREENSHOT/ad02232b-03c5-4421-b298-56890b2bdff9.png@webp",
                "https://static.rustore.ru/imgproxy/12t6491nw6gHHtvuZasA08X_L-f2H890NkqM6aGALD8/preset:web_scr_prt_384/plain/https://static.rustore.ru/apk/1504831423/content/SCREENSHOT/5a9baa56-605d-48d7-9480-96d5226a29f6.png@webp"
            ),
            description = "Просмотр и организация фото и видео на устройстве."
        ),
        AppDetailsDto(
            id = "e2f3a8b9-1234-4mno-4425-76c1d2e3f4a5",
            name = "Браузер",
            developer = "Utilities Team",
            category = "UTILITIES",
            ageRating = 3,
            size = 15.6f,
            iconUrl = "https://static.rustore.ru/imgproxy/Rw13kRzg1ytg9BQ8ujFZ_gt0wx3s_Br8zNxXU75URW8/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/2063615530/content/ICON/d5706435-0eac-44de-bfdd-a46240cfd7ee.png@webp",
            screenshotUrlList = listOf(
                "https://static.rustore.ru/imgproxy/RfxXxUsw8vmoowhPt9q33Z6YsPirqZM9ZWU8bQvvJMI/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/2063615530/content/SCREENSHOT/c8da6f7c-5c03-43fe-9491-6c038b24184e.jpg@webp",
                "https://static.rustore.ru/imgproxy/oP13xw7tHNvihUaVGnosoPs3iim8P0Evnn6B8jUecVc/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/2063615530/content/SCREENSHOT/32a9f0e3-49c1-4731-a7d3-c08aa8121f7d.jpg@webp",
                "https://static.rustore.ru/imgproxy/HklXuT_bUkehCQu1fhu6YOVsLyOAkUYCXYk2YoiWymg/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/2063615530/content/SCREENSHOT/ce3fdc58-348d-4613-81f6-5d8378d309a4.jpg@webp",
                "https://static.rustore.ru/imgproxy/868-_lKz9wRmv14dyMFLI8ZVIvM8fgfY4SmjZltc92s/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/2063615530/content/SCREENSHOT/8abbe1e8-00b3-461a-a143-66ab5611ea3a.jpg@webp",
                "https://static.rustore.ru/imgproxy/2Jpv8sr-9L3PeWb6gJINfzDUHqKdMkIzeFq24UI3FYM/preset:web_scr_prt_384/plain/https://static.rustore.ru/apk/2063615530/content/SCREENSHOT/20b28d82-066b-45b6-990c-ca7155ef4acf.jpg@webp"
            ),
            description = "Быстрый веб‑просмотр с вкладками и приватным режимом."
        ),
        AppDetailsDto(
            id = "f6g7h5i4-5678-4pqr-3316-98b7a6c5d4e3",
            name = "Почта",
            developer = "Social Team",
            category = "SOCIAL",
            ageRating = 3,
            size = 11.3f,
            iconUrl = "https://static.rustore.ru/imgproxy/Bi75zF-8GLpg68A5OU3Ev4NdPShOW2iFX9660_L6-Wc/preset:web_app_icon_62/plain/https://static.rustore.ru/2025/12/9/83/apk/319999679/content/ICON/6b55bc25-3152-485f-b58b-4496b7db6e89.png@webp",
            screenshotUrlList = listOf(
                "https://static.rustore.ru/imgproxy/dz2eNg0VS2B1jP4qby01W1RXNkgFsaXrlpeaNkLIsQc/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/12/8/1a/apk/319999679/content/SCREENSHOT/ea2679bf-fe62-454d-8ee1-cf09c97b2a57.jpg@webp",
                "https://static.rustore.ru/imgproxy/qAksN8cbg3Lm4LRkBCCNP6ZDM-E8SVLz7YJlSk3H4ik/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/12/8/fa/apk/319999679/content/SCREENSHOT/0b7191f7-9699-4f5f-8dd5-39f241e392f2.jpg@webp",
                "https://static.rustore.ru/imgproxy/o6VKAh50hjr7GGj16ixne4Hm9HBZW638uer9c5BSZuw/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/12/8/34/apk/319999679/content/SCREENSHOT/4d4ff179-3297-435f-bcc7-ae721594ee12.jpg@webp",
                "https://static.rustore.ru/imgproxy/B3DM6IvWV4LgWTHmoerSenVR09spKQde8iOiDI-xrcU/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/12/8/a0/apk/319999679/content/SCREENSHOT/0373bb8e-3a06-4b94-b992-2c396799ea93.jpg@webp",
                "https://static.rustore.ru/imgproxy/5sWo9KpIQ0uMUfLW00oVhZUkAraJTf3EllKiyNqcUPU/preset:web_scr_prt_384/plain/https://static.rustore.ru/2025/12/8/cc/apk/319999679/content/SCREENSHOT/bbb27d58-0125-44aa-9c59-864235999eb1.jpg@webp"
            ),
            description = "Проверяйте письма, сортируйте по папкам и настраивайте уведомления."
        ),
        AppDetailsDto(
            id = "g9h8i7j6-9012-4stu-2247-54a3b2c1d8e7",
            name = "Карты",
            developer = "Maps Team",
            category = "MAPS",
            ageRating = 3,
            size = 45.2f,
            iconUrl = "https://static.rustore.ru/imgproxy/uk4eQOt5jhw-yCSHwIZYwyGLHRduRvjqAhvysY__Wmc/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/586431/content/ICON/a5f2fe7d-cd63-4f3f-a2f8-40d997c1d6f4.png@webp",
            screenshotUrlList = listOf(
                "https://static.rustore.ru/imgproxy/JrTEpm2v6kIEPkboOOCN_vxEE3yb2psYxpNpEItWMXc/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/10/1/6e/apk/586431/content/SCREENSHOT/72c31baa-226b-422e-867e-79399c69066d.png@webp",
                "https://static.rustore.ru/imgproxy/jNJqOwByaki1YM21gkqvr_2I-jIgyfwnbEj8QRNVQkw/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/10/1/0a/apk/586431/content/SCREENSHOT/a22680e7-bbea-459e-bf1e-55bbecd69828.jpg@webp",
                "https://static.rustore.ru/imgproxy/eKMhC2yvBmDM3ZRwVQqkjS0d5RNJ0w-ZMQLrMMU4RDQ/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/20/b4/apk/586431/content/SCREENSHOT/236fb404-4f3c-491e-9880-549f393e755e.jpg@webp",
                "https://static.rustore.ru/imgproxy/XtoYZzLMRDerDjzhv1jIFj9n6wUF3kI_9BgapdCNcHM/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/7/10/bd/apk/586431/content/SCREENSHOT/31cfa943-2c42-4d03-aa2d-6a70d2df360f.jpg@webp",
                "https://static.rustore.ru/imgproxy/l1nkpdF5yJH9iKhRQQm8XurlDG1LYsjXa9npyWGdknE/preset:web_scr_prt_384/plain/https://static.rustore.ru/2026/7/10/fb/apk/586431/content/SCREENSHOT/e299d4ff-7fb2-4ba0-88d6-95f2c50a8882.jpg@webp"
            ),
            description = "Стройте маршруты, ищите места и смотрите пробки в реальном времени."
        ),
        AppDetailsDto(
            id = "h3i2j1k0-3456-4vwx-1158-65d4c3b2a1f9",
            name = "Музыка",
            developer = "Music Team",
            category = "MUSIC",
            ageRating = 3,
            size = 32.7f,
            iconUrl = "https://static.rustore.ru/imgproxy/a6CTARChSVcRpLQhaHk0wimKYcDETMj_9avuVNogvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/2025/9/25/7c/apk/2063659571/content/ICON/39361900-91fd-4769-b212-18d67ee3cbc4.png@webp",
            screenshotUrlList = listOf(
                "https://static.rustore.ru/imgproxy/zqT9vdFDriBEYNnPKS9sd8eaJ_kK8aBZByQ10CIXFUk/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/9/25/20/apk/2063659571/content/SCREENSHOT/dd18dd6b-baa5-4f7c-81b0-652f21f9c69a.png@webp",
                "https://static.rustore.ru/imgproxy/fOYDc9dNk4JrHPiODTPhOqHu01dO6U0caqvdVjreo9Q/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/9/25/37/apk/2063659571/content/SCREENSHOT/a698a874-5d30-4483-a2d4-f1e2de898c12.png@webp",
                "https://static.rustore.ru/imgproxy/6ucBFIKLy51thdl79DLldg7VCpDG7tliP8b9i_4XHAI/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/9/25/17/apk/2063659571/content/SCREENSHOT/b3588062-7867-4d88-8247-a1d1e3515dfb.png@webp",
                "https://static.rustore.ru/imgproxy/yls5lBC2YbqpnlKu3GFO63XG4qcR43wQT6Bc8clf6vg/preset:web_scr_prt_162/plain/https://static.rustore.ru/2025/9/25/9b/apk/2063659571/content/SCREENSHOT/1c468205-8557-441f-872a-2cb2c98c9730.png@webp",
                "https://static.rustore.ru/imgproxy/OgL-JafQ--HBMC5JM8bjVl9viwcWJCmNhpj4-Dlc7Eo/preset:web_scr_prt_384/plain/https://static.rustore.ru/2025/9/25/73/apk/2063659571/content/SCREENSHOT/6dcec619-2214-4463-b8ff-2129d63f64a4.png@webp"
            ),
            description = "Слушайте любимые треки, создавайте плейлисты и скачивайте офлайн."
        ),
        AppDetailsDto(
            id = "i6j5k4l3-7890-4yz0-9969-76e5d4c3b2a8",
            name = "Фитнес",
            developer = "Health Team",
            category = "HEALTH",
            ageRating = 7,
            size = 18.9f,
            iconUrl = "https://static.rustore.ru/imgproxy/yJN26qd3O6sGM6VzPSseSjEgH1dApzB2F5Q42CFjt60/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/2063586598/content/ICON/5ab5364c-3681-4477-a81d-d70eaa0e0f48.jpg@webp",
            screenshotUrlList = listOf(
                "https://static.rustore.ru/imgproxy/6Tf3ttPr0rthxGCQviUlLVEUvU9vzbsAQxMUqj3kG9g/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/2063586598/content/SCREENSHOT/3f2dbe54-a258-487f-84bd-4ab409e4bd7f.jpg@webp",
                "https://static.rustore.ru/imgproxy/iwTG0XOqchNRWG_vtpkyUzNP2xVW4YgDKZike8YSVBQ/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/2063586598/content/SCREENSHOT/5a95d950-881a-41fc-8050-f8ea6dcc7321.jpg@webp",
                "https://static.rustore.ru/imgproxy/IJY4R-z-i1zwlQVoKjPsUEgmakUyE0KHukZ_24u8JFY/preset:web_scr_prt_384/plain/https://static.rustore.ru/apk/2063586598/content/SCREENSHOT/5e9258bd-3083-45cd-b4b4-90ff14df0632.jpg@webp",
                "https://static.rustore.ru/imgproxy/B1nplcuk4ldVI3M6DKk21cHVct-1B8q3uJvAqvi5cN0/preset:web_scr_prt_384/plain/https://static.rustore.ru/apk/2063586598/content/SCREENSHOT/954d3f4c-de1c-4274-a8fd-4662abe706a8.jpg@webp",
                "https://static.rustore.ru/imgproxy/KiVuLBot0-sUZ-V8RLhWooZlZ7KSxgxbPCMsnpV0Wv8/preset:web_scr_prt_384/plain/https://static.rustore.ru/apk/2063586598/content/SCREENSHOT/71b5f781-fc55-4048-b2d8-bd9d2f1bf5e1.jpg@webp"
            ),
            description = "Отслеживайте шаги, калории и прогресс тренировок с персональными целями."
        ),
        AppDetailsDto(
            id = "j9k8l7m6-1234-4abc-8870-87f6e5d4c3b1",
            name = "Новости",
            developer = "News Team",
            category = "NEWS",
            ageRating = 3,
            size = 13.5f,
            iconUrl = "https://static.rustore.ru/imgproxy/yPVJ1PnFS_X61ESioaaEijuU7Bdku_EHCxMGDPAWJmw/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/2063505920/content/ICON/8d52cc0b-9ed3-469e-98dd-cc6246e23abf.@webp",
            screenshotUrlList = listOf(
                "https://static.rustore.ru/imgproxy/YM1XMIfpkQLc7ZMyXHzW9dh5IHT2ttrE2gUXybwfZP0/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/2063505920/content/SCREENSHOT/e071d548-92d1-4625-8927-b2d535093000.@webp",
                "https://static.rustore.ru/imgproxy/28YO75-Zl_NoO-OH1t6ij2JPD5v7_yUkL1M_RF0isXU/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/2063505920/content/SCREENSHOT/4309d736-9432-4b65-9ad0-1a6c7e08745c.@webp",
                "https://static.rustore.ru/imgproxy/onL2YRiErOmLOvl2VhFiDNkmE2EbGJ27mq4fXFZdcz0/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/2063505920/content/SCREENSHOT/da6e6ad3-9480-4d5a-8d98-3f0a97c08dbe.@webp",
                "https://static.rustore.ru/imgproxy/ON5ylqE8_feDeieDGdTQqSm-8_kwkoKTKCob-NGMF0k/preset:web_scr_prt_162/plain/https://static.rustore.ru/apk/2063505920/content/SCREENSHOT/257544fc-484d-4704-9325-38f734c6975a.@webp",
                "https://static.rustore.ru/imgproxy/ngzEQSomNacm9eP9gHwd4Sc3G2K14b5J297r3ryKUHE/preset:web_scr_prt_384/plain/https://static.rustore.ru/apk/2063505920/content/SCREENSHOT/9e77c480-50f9-4922-be98-0fafd1adc6ee.@webp"
            ),
            description = "Читайте свежие статьи, настраивайте ленты по интересам и сохраняйте важное."
        ),
        AppDetailsDto(
            id = "k2l3m4n5-5678-4def-7781-98g7f6e5d4c2",
            name = "Образование",
            developer = "Education Team",
            category = "EDUCATION",
            ageRating = 5,
            size = 267.5f,
            iconUrl = "",
            screenshotUrlList = listOf(),
            description = "Читайте свежие статьи, настраивайте ленты по интересам и сохраняйте важное."
        ),
    )

    val similarApps: List<SimilarAppDto> = List(15) { index ->
        SimilarAppDto(
            id = "similar-app-$index",
            name = "Гильдия Героев: Экшен ММО РПГ: $index",
            iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp"
        )
    }
}