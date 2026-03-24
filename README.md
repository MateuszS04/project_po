📦 Warehouse Management System

Aplikacja desktopowa do zarządzania magazynem, zbudowana w języku Java z wykorzystaniem biblioteki JavaFX. Projekt oferuje kompleksowe rozwiązanie do obsługi kont użytkowników, monitorowania stanów magazynowych oraz zarządzania pracownikami.



🚀 Funkcje projektu

Zarządzanie Użytkownikami: Logowanie, rejestracja oraz moduły zmiany hasła i danych logowania.

Monitorowanie Magazynu: Sprawdzanie wolnej przestrzeni, przeglądanie dostaw oraz stanów magazynowych.

Panel Pracownika: Dedykowane menu dla pracowników z możliwością zarządzania przechowywanymi towarami.

Integracja z Bazą Danych: Obsługa lokalnej bazy danych SQLite do trwałego przechowywania informacji.

Modern UI: Interfejs wzbogacony o biblioteki takie jak BootstrapFX oraz ControlsFX.



🛠 Technologie

Java: 23

JavaFX: 17.0.6 (wsparcie dla UI)

Maven: Zarządzanie zależnościami i cyklem życia projektu.

SQLite: Lokalna baza danych SQL.

Biblioteki dodatkowe:

ControlsFX (zaawansowane kontrolki UI)

FormsFX (obsługa formularzy)

Ikonli (ikony w interfejsie)

BootstrapFX (stylizacja CSS)



📋 Wymagania

Przed uruchomieniem upewnij się, że masz zainstalowane:

JDK 23 (lub nowsze)

Maven (opcjonalnie, projekt zawiera mvnw)



⚙️ Instalacja i Uruchomienie

Sklonuj repozytorium:

```Bash

git clone https://github.com/twoj-uzytkownik/Warehouse.git
cd Warehouse
Zbuduj projekt:
Użyj Mavena, aby pobrać wszystkie biblioteki:
```

```Bash

./mvnw clean install
Uruchom aplikację:
Możesz uruchomić projekt bezpośrednio przez Mavena:
```
```Bash

./mvnw javafx:run
Lub otwórz projekt w IntelliJ IDEA i uruchom klasę ProjectApplication.java.
```

📂 Struktura Projektu

src/main/java/com/example/demo1 – Klasy kontrolerów (MVC) oraz logika aplikacji.

src/main/resources – Pliki .fxml (widoki), arkusze stylów CSS oraz grafiki.

data_base – Folder zawierający pliki bazy danych .sqlite.
