/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     03.04.2023 14:05:20                          */
/*==============================================================*/



alter table ZO_ANIMAL
    drop foreign key FK_ZO_ANIMAL$ANIMAL_TYPE_ID;

alter table ZO_TANK_ANIMAL
    drop foreign key FK_ZO_TANK_ANIMAL$TANK_ID;

alter table ZO_TANK_ANIMAL
    drop foreign key FK_ZO_TANK_ANIMAL$ANIMAL_ID;


alter table ZO_ANIMAL
    drop foreign key FK_ZO_ANIMAL$ANIMAL_TYPE_ID;

drop table if exists ZO_ANIMAL;

drop table if exists ZO_ANIMAL_TYPE;

drop table if exists ZO_TANK;


alter table ZO_TANK_ANIMAL
    drop foreign key FK_ZO_TANK_ANIMAL$TANK_ID;

alter table ZO_TANK_ANIMAL
    drop foreign key FK_ZO_TANK_ANIMAL$ANIMAL_ID;

drop table if exists ZO_TANK_ANIMAL;


alter table ZOO_FOOD_ORDER
drop foreign key FK_ZOO_FOOD_REFERENCE_ZO_ANIMA;

alter table ZOO_FOOD_ORDER
drop foreign key FK_ZOO_FOOD_REFERENCE_ZO_FOOD;


alter table ZOO_FOOD_ORDER
drop foreign key FK_ZOO_FOOD_REFERENCE_ZO_ANIMA;

alter table ZOO_FOOD_ORDER
drop foreign key FK_ZOO_FOOD_REFERENCE_ZO_FOOD;

drop table if exists ZOO_FOOD_ORDER;


alter table ZO_FOOD
drop foreign key FK_ZO_FOOD_REFERENCE_ZO_FOOD_;

alter table ZO_FOOD
drop foreign key FK_ZO_FOOD_REFERENCE_ZO_FOOD_;

drop table if exists ZO_FOOD;


drop table if exists ZO_UNIT;
