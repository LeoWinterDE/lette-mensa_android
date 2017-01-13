##Projekt aufsetzen: (Eclipse)

####Vorraussetzungen:
1. Installierte Android SDK (Android API Level 22)
2. Android Development Tools für Eclipse (Android API Level 22)
3. EGit Plugin für Eclipse

####1. Android Support Libs:
> 
Zum Importieren der Android Support Libs, die für dieses Projekt benötigt werden wählen Sie File -> Import.<br>
In dem neuen Fenster wählen Sie im Ordner Android "Existing Android Code Into Workspace" aus und bestätigen mit Next. Danach geben Sie im Feld "Root Directory" den Pfad zur Android SDK an und hängen "\extras\android\support\v7\appcompat" an.<br> Dies könnte dann so aussehen:<br><br>
"C:\android-sdk-windows\extras\android\support\v7"<br><br>
In der Liste "Projects" wählen Sie dann "appcompat" und "recyclerview".
Im Anschluss Finish drücken.<br>
Zusätzlich müssen Sie das Projekt "TestActivity" zu einem Library Projekt machen. Dazu wählen Sie das Projekt mit Rechtsklick aus, wählen "Properties" und setzten im Menüpunkt "Android" ganz unten den Haken bei "Is Library".

####2. Klonen des Projekts:
> 
Zum Klonen des Projekts wählen Sie File -> Import.<br>
In dem neuen Fenster wählen Sie im Ordner Git "Projects from Git" aus und bestätigen mit Next.<br>
Im folgenden Fenster wählen Sie "Clone URI" aus und bestätigen mit Next.<br>
Im Feld "URI" geben Sie nun diesen Link ein: https://github.com/MarcelHenze/MensaAppV2.git und bestätigen zwei mal mit Next. In dem folgendem Fenster wählen Sie den Ort aus an dem das Projekt gespeichert werden soll und bestätigen zwei mal mit Next und ein mal mit Finish.<br><br>
Zuletzt müssen Sie die Datei "project.properties" im Hauptprojektverzeichnis anlegen und das importierte Projekt anschließend mit den in Schritt 1 importierten Libraries verbinden und Android API Level 22 auswählen. Dazu wählen Sie das Projekt mit Rechtsklick aus, wählen "Properties" und fügen im Menüpunkt "Android" unter "Library" die beiden Projekte aus Schritt 1 hinzu.

##Fertig!
