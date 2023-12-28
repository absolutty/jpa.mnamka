<h1>Mňamka</h1>

Použité technológie:
- Spring Boot
- JPA Hibernate
- databáza PostgreSQL
- HTML
- CSS (BootStrap ver. 4.5.2)
- JavaScript (jQuery)
- Ajax

<h3> 
  Webová aplikácia pre fiktívnu reštauráciu s názvom <strong>Mňamka</strong>
</h3>

Má 4 základné podstránky:
- About
- Menu
- Home
- Kontakt

Umožňuje:
- prezerať jedlá ktoré sú v ponuke
- vytvoriť objednávku
- v košíku je možné objednávku upraviť alebo vytvoriť

![Alt Text](https://github.com/absolutty/jpa.mnamka/blob/developement/images/app-showcase_1.gif?raw=true)

<h3> 
  Autorizácia rolí
</h3>

Aplikácia obsahuje dve základné role: USER a ADMIN <br>
Pokiaľ je používateľ prihlasený ako admin, tak vie:
- upravovať stav vytvorených objednávok
  - PENDING("Čakajúca")
  - PROCESSING("Spracovávaná")
  - COMPLETED("Dokončená")
- upravovať databázu používateľov, kategórií jedál a jednotlivých jedál

![Alt Text](https://github.com/absolutty/jpa.mnamka/blob/developement/images/app-showcase_2.gif?raw=true)
