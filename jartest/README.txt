0. wypakować zawartość do jednego folderu

1. wrzucić driver do Chrome od selenium bezpośrednio na dysk C:\

2. sprawdzić czy działa wpisując komendę:
	java -jar seleniumGrzesiek-1.0-SNAPSHOT-jar-with-dependencies.jar
   będąc oczywiście w odpowiednim folderze w konsoli

3. jeśli plik output-DATA-GODZINA.csv zawiera 4 linijki:
	Grafika interaktywna,https://edytor.epodreczniki.pl/x/D1AxGGJNP?lang=pl,CHE_0004
	Gra edukacyjna,https://edytor.epodreczniki.pl/x/DBRS9BNbh?lang=pl,CHE_0006
	Symulacja interaktywna,https://edytor.epodreczniki.pl/x/DRGOPQVs2?lang=pl,CHE_0007
	Audiobook,https://edytor.epodreczniki.pl/x/DUmXgeTEr?lang=pl,CHE_0008
   to zajebiście

4. zastąpić plik input.csv swoim długaśnym plikem csv w formacie:
	https://edytor.epodreczniki.pl/x/D1AxGGJNP?lang=pl;CHE_0004
	link;kod
   ważny jest średnik

6. w pliku output separatorem jest "," proszę się nie zdziwić

5. odpalić

7. poczekać 10 sekund * ilość lnijek +/- 30 sekund

8. automatyzacja rządzi
