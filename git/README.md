

## Git

### Файлы конфигурации

-- local (default)
* <progect>/.git/config

-- global
* ~/.gitconfig
* C:\Users\<USERNAME>\.gitconfig  - _в Windows_

-- system
* /etc/gitconfig
* C:\Program Files\Git\etc\gitconfig - _в Windows_
* C:\ProgramData\Git\etc\gitconfig - _в Windows_


### Команды


### Конфигурация
```
git config --global user.name "Andrey Miroshnik"
git config --global user.email "maimaimai1965@gmail.com"

git config --get-all user.name
git config --get-all user.email

git config --help
git config -h
```

### Создание репозитория
В каталоге проекта вводим:
```
git init
```
Создается каталог _.git_, содержащий репозитарий. 


### Работа с файлами
```
# Добавление нового файла в Stage (Index) область:
git add <file>

# Добавление нового файла в из Stage области в репозиторий:
git commit -m "commit message"

# Просмотр последнего коммита:
git show --pretty=fuller

# Добавление в Index всех изменений (в том числе и всех новых файлов):
git add .

# Добавление всех измененных отслеживаемых файлов в Stage область и выполнение commit
git -a -m "commit message"


```
