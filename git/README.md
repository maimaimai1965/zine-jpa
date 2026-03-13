

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

# Добавление нового/измененного(удаленного) файла в Stage(Index) области в(из) репозиторий:
git commit -m "commit message" <file>

# Добавление новых/измененных(удаленных) файлов в Stage(Index) области в(из) репозиторий:
git commit -m "commit message"

# Просмотр последнего коммита:
git show --pretty=fuller

# Добавление в Stage(Index) всех изменений (в том числе и всех новых файлов):
git add .

# Одновременное добавление всех измененных отслеживаемых файлов в Stage(Index) область
# и выполнение commit (перенос их в репозитарий):
git -a -m "commit message"

# изменение названия файла в рабочей директории и запись нового файла в Stage(Index) область (как переименнованного старого):
git mv <old_name> <new_name>

# Отмена всех незакоммиченных изменений (в рабочем каталоге):
git checkout -f 


# Сохранение незакоммиченных изменений во временную области:
git stash

# Восстановление ранее сохраненных измений из временной области:
git stash pop


# Восстановление файла из коммита (запишется в рабочий каталог и в Stage(Index) область):
git checkout <commit_id> <file>

# Востановление файла из последнего коммита:
git checkout -- <file>

# Удаление в working area всех неотслеживаемых (untracked) файлов (флаг d), пустых каталогов (флаг x):
git clean -dfx
```

### Просмотр коммитов - _show_
```
# Просмотр коммита, на котором стоит HEAD (отличий в файлах):
git show

# Просмотр файла в другой ветке:
git show <branch_name>:<file>
```

### Просмотр истории - _reflog_
```
# Просмотр истории перемещений HEAD и веток (файл .git/logs/HEAD):
git reflog

# Просмотр файла в другой ветке:
git show <branch_name>:<file>
```

### Branch (ветки)
```
# Просмотр текущей ветки (-v показывает последний комит)
git branch -v

# Создание новой ветки:
git branch <branch_name>

# Переключение на ветку:
git checkout <branch_name>

# Создание новой ветки + переключение на нее (всех изменения остаются в новой ветке):
git checkout -b <branch_name>
```

#### **merge**
```
# Слияние ветки fix в ветку master:
git checkout master
git merge fix

# Отмена merge:
git merge --abort

# Удаление ветки:
git branch -d <branch_name>
```

#### **reset**
```
# Жесткое восстановление файлов в working directory к состоянию указанного коммита:
git reset --hard <commit_id>

# Мягкое восстановление - перенос указателя ветки (с HEAD) на указанный коммит. Все измения файлов в working directory
# остаются и index область не очищаетя:
git reset --soft <commit_id>

# Смешанное восстановление (без флага) - перенос указателя ветки (с HEAD) на указанный коммит. Все измения файлов в
# working directory остаются, а index область очищаетя:
git reset <commit_id>

# Удаление файла из index области:
git reset <файл>

```



