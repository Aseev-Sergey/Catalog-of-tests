# Каталог тестов
Программа представляет собой навигацию по главам файла, который открывает пользователь при нажатии кнопки "Загрузить каталог". При выборе нужной главы, пользователь, по нажатию кнопки "В редакторе", может перейти на эту главу в файле для редактирования. В качестве редактора использовался Notepad++.
## Диаграмма классов

![0](https://user-images.githubusercontent.com/85245803/122871478-bfd4fa00-d337-11eb-9be5-cb28ab5fd434.png)

Класс FillCatalog отвечает за загрузку глав в список навигации

Классы ControllerCatalog и ControllerEditor - классы-контроллеры для главного окна и окна-редактора

## Работа программы
Запуск программы

![1](https://user-images.githubusercontent.com/85245803/122384964-e6301980-cf74-11eb-93f3-b3f5ff146bf2.png)

Работа кнопки "Загрузить каталог"

![2](https://user-images.githubusercontent.com/85245803/122385011-f3e59f00-cf74-11eb-9829-f61de2345a03.png)
![3](https://user-images.githubusercontent.com/85245803/122385029-fa741680-cf74-11eb-8e67-386aabb71395.png)
![4](https://user-images.githubusercontent.com/85245803/122385037-fc3dda00-cf74-11eb-8bfe-c66c413b32c3.png)

Работа кнопки "В редакторе"

![5](https://user-images.githubusercontent.com/85245803/122385125-14155e00-cf75-11eb-8298-edc02579fa0c.png)
![2](https://user-images.githubusercontent.com/85245803/122871838-41c52300-d338-11eb-8915-002fd98f73df.png)

При попытке загрузки файла формата, не предусмотренного для обработки программой, в окне состояния загрузки каталога появится надпись "Неправильный формат", и каталог не будет загружен

![7](https://user-images.githubusercontent.com/85245803/122386135-3cea2300-cf76-11eb-93bd-5441e168e1ba.png)
![8](https://user-images.githubusercontent.com/85245803/122386139-3eb3e680-cf76-11eb-8be8-8876365b5190.png)
