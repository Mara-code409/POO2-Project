# E-Learning Platform — Programare Avansată pe Obiecte

Proiect individual realizat în Java pentru cursul de Programare Avansată pe Obiecte.

---

## Tema: Platformă E-Learning

Sistem de gestiune a cursurilor online, cu suport pentru studenți, instructori, cursuri, module și quizuri.

---

## Tipuri de obiecte 

| Nr. | Clasă | Atribute principale |
|-----|-------|---------------------|
| 1 | `Utilizator` | id, nume, email, parola, rol |
| 2 | `Student` *(extends Utilizator)* | cursuri înscrise |
| 3 | `Instructor` *(extends Utilizator)* | cursuri predate, specializare |
| 4 | `Curs` | id, titlu, descriere, instructor, categorie, pret |
| 5 | `Modul` | id, titlu, continut, durata, curs |
| 6 | `Quiz` | id, intrebari, curs, punctaj maxim |
| 7 | `Intrebare` | id, text, variante raspuns, raspuns corect |
| 8 | `Inscriere` | id, student, curs, data, progres, nota finala |

---

## Acțiuni / Interogări 

| Nr. | Acțiune |
|-----|---------|
| 1 | Înregistrare student |
| 2 | Adăugare curs nou (de către instructor) |
| 3 | Înscriere student la curs |
| 4 | Adăugare modul la curs |
| 5 | Vizualizare cursuri disponibile (cu filtrare după categorie) |
| 6 | Vizualizare cursuri la care este înscris un student |
| 7 | Susținere quiz și calculare scor |
| 8 | Vizualizare progres student la un curs |
| 9 | Vizualizare toți studenții înscriși la un curs |
| 10 | Ștergere curs |

---
