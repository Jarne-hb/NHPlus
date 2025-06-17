# Testing

Dokumentiert über diese Markdown-Datei eure Tests. Führt dazu in einer Tabelle alle Testfälle auf,
und gebt in einer zweiten Spalte das Ergebnis des jeweiligen Tests an. Über die Editor-Ansicht rechts oben
gelangst du in die Ansicht, in der du Inhalte hinzufügen kannst. Die Markdown-Syntax kannst du unter 
https://markdown.de/ einsehen.

# Testfalldokumentation – NHPlus

| User Story | Testfall | Beschreibung | Ergebnis                                                                                                                                                                                                                                                                                   |
|------------|----------|--------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Vermögensstand entfernen** | TF_1 | Alle Patienten anzeigen – Spalte Vermögensstand wird nicht angezeigt | Die Spalte „Vermögensstand“ wird in der Patientenübersicht nicht mehr angezeigt.                                                                                                                                                                                                           |
|  | TF_2 | Datenbank überprüfen – Spalte „Assets“ existiert nicht mehr | In der Tabelle patient der Datenbank ist das Feld „Assets“ entfernt.                                                                                                                                                                                                                       |
| **Pflegemodul hinzufügen** | TF_1 | Alle Pfleger:innen anzeigen | **Erster Versuch:** <br> Beim Klick auf den Pfleger:innen-Button verschwindet die ID Spaltenüberschrift<br> **Zweiter Versuch:**<br> Beim Klick auf den Pfleger:innen-Button werden alle Daten tabellarisch korrekt angezeigt.                                                             |
|  | TF_2 | Pfleger:in ändern | Telefonnummer wurde geändert und mit Enter bestätigt. Änderung erscheint in der View und in der Datenbank.                                                                                                                                                                                 |
|  | TF_3 | Pfleger:in hinzufügen | Neue Pflegekraft wurde erfolgreich hinzugefügt und erscheint in der View und der Datenbank.                                                                                                                                                                                                |
|  | TF_4 | Pfleger:in löschen | Pflegekraft wurde entfernt und ist nicht mehr in der View oder Datenbank sichtbar.                                                                                                                                                                                                         |
| **Pflegekraft zur Behandlung hinzufügen und anzeigen** | TF_1 | Combobox zeigt alle Pflegekräfte an | Beim Öffnen der Combobox werden alle Pflegekräfte korrekt gelistet.                                                                                                                                                                                                                        |
|  | TF_2 | Pflegekraft in Behandlung anzeigen | **Erster Versuch:** <br> Beim Versuch die Behandlung zu öffnen, tritt ein Fehler auf. In der Console wird angezeigt das der caregiver <code>null</code> ist <br> **Zweiter Versuch:** <br> Nach dem öffnen der Behandlung werden Name und Telefonnummer der Pflegekraft korrekt angezeigt. |
|  | TF_3 | Neue Behandlung mit Pflegekraft anlegen | Pflegekraft wird korrekt gespeichert und in der Detailansicht angezeigt.                                                                                                                                                                                                                   |
| **Login hinzufügen** | TF_1 | Login mit korrekten Daten | Benutzer wird erfolgreich eingeloggt und zur Hauptansicht weitergeleitet.                                                                                                                                                                                                                  |
|  | TF_2 | Login mit falschen Daten | Fehlermeldung erscheint, Zugang wird verweigert.                                                                                                                                                                                                                                           |_                                                                                           

# Weitere Bugs – NHPlus

| Aufgetretener Bug                                                                                                                                                                                         | Status |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------|
| Beim Tab drücken in dem ersten Eingabefeld, beim Erstellen eines neuen Patienten in der AllPatientView, springt der zum dritten anstatt zum zweiten Feld. Erst danach springt er zum zweiten Eingabefeld. | fixed ✅ |
| Wenn ein:e Pfleger:in gelöscht wurde und man versucht eine Behandlung mit dem/der Pfleger:in zu öffnen, so lässt sich diese nicht öffnen da das caregiver Objekt <code>null</code> ist.                   | fixed ✅ |

