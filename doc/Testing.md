# Testing

Dokumentiert über diese Markdown-Datei eure Tests. Führt dazu in einer Tabelle alle Testfälle auf,
und gebt in einer zweiten Spalte das Ergebnis des jeweiligen Tests an. Über die Editor-Ansicht rechts oben
gelangst du in die Ansicht, in der du Inhalte hinzufügen kannst. Die Markdown-Syntax kannst du unter 
https://markdown.de/ einsehen.

# Testfalldokumentation – NHPlus

| User Story | Testfall | Beschreibung | Ergebnis                                                                                                   |
|------------|----------|--------------|------------------------------------------------------------------------------------------------------------|
| **US_1: Vermögensstand entfernen** | TF_1 | Alle Patienten anzeigen – Spalte Vermögensstand wird nicht angezeigt | Die Spalte „Vermögensstand“ wird in der Patientenübersicht nicht mehr angezeigt.                           |
|  | TF_2 | Datenbank überprüfen – Spalte „Assets“ existiert nicht mehr | In der Tabelle patient der Datenbank ist das Feld „Assets“ entfernt.                                       |
| **US_2: Pflegemodul hinzufügen** | TF_1 | Alle Pfleger:innen anzeigen | Beim Klick auf den Pfleger:innen-Button werden alle Daten tabellarisch korrekt angezeigt.                  |
|  | TF_2 | Pfleger:in ändern | Telefonnummer wurde geändert und mit Enter bestätigt. Änderung erscheint in der View und in der Datenbank. |
|  | TF_3 | Pfleger:in hinzufügen | Neue Pflegekraft wurde erfolgreich hinzugefügt und erscheint in der View und der Datenbank.                |
|  | TF_4 | Pfleger:in löschen | Pflegekraft wurde entfernt und ist nicht mehr in der View oder Datenbank sichtbar.                         |
| **US_3: Pflegekraft zur Behandlung hinzufügen und anzeigen** | TF_1 | Combobox zeigt alle Pflegekräfte an | Beim Öffnen der Combobox werden alle Pflegekräfte korrekt gelistet.                                        |
|  | TF_2 | Pflegekraft in Behandlung anzeigen | Name und Telefonnummer der Pflegekraft werden korrekt dargestellt.                                         |
|  | TF_3 | Neue Behandlung mit Pflegekraft anlegen | Pflegekraft wird korrekt gespeichert und in der Detailansicht angezeigt.                                   |
| **US_4: Login hinzufügen** | TF_1 | Login mit korrekten Daten | Benutzer wird erfolgreich eingeloggt und zur Hauptansicht weitergeleitet.                                  |
|  | TF_2 | Login mit falschen Daten | Fehlermeldung erscheint, Zugang wird verweigert.                                                           |_                                                                                           
