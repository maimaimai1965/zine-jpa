/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     03.04.2023 15:49:18                          */
/*==============================================================*/

/*==============================================================*/
/* Table: ZO_ANIMAL_TYPE                                        */
/*==============================================================*/
create table ZO_ANIMAL_TYPE
(
    ANIMAL_TYPE_ID       int not null auto_increment  comment '',
    NAME                 char(100) not null  comment '',
    DESCR                char(255)  comment '',
    primary key (ANIMAL_TYPE_ID)
);

/*==============================================================*/
/* Table: ZO_ANIMAL                                             */
/*==============================================================*/
create table ZO_ANIMAL
(
   ANIMAL_ID            bigint not null auto_increment  comment '',
   NICKNAME             char(30) not null  comment '',
   ANIMAL_TYPE_ID       int not null  comment '',
   GENDER               char(1) not null  comment '',
   BIRTH_DT             date  comment '',
   DEATH_DT             date  comment '',
   DESCR                char(255)  comment '',
   primary key (ANIMAL_ID)
);
alter table ZO_ANIMAL add constraint FK_ZO_ANIMAL$ANIMAL_TYPE_ID foreign key (ANIMAL_TYPE_ID)
    references ZO_ANIMAL_TYPE (ANIMAL_TYPE_ID) on delete restrict on update restrict;


/*==============================================================*/
/* Table: ZO_TANK                                               */
/*==============================================================*/
create table ZO_TANK
(
   TANK_ID              int not null auto_increment  comment '',
   TANK_TYPE            char(1) not null  comment '',
   NUMBER_CD            char(20) not null  comment '',
   DESCR                char(255)  comment '',
   primary key (TANK_ID)
);

/*==============================================================*/
/* Table: ZO_TANK_ANIMAL                                        */
/*==============================================================*/
create table ZO_TANK_ANIMAL
(
    TANK_ANIMAL_ID       bigint not null auto_increment  comment '',
    TANK_ID              int not null  comment '',
    ANIMAL_ID            bigint not null  comment '',
    FROM_DT              datetime not null  comment '',
    TO_DT                datetime  comment '',
    primary key (TANK_ANIMAL_ID)
);
alter table ZO_TANK_ANIMAL add constraint FK_ZO_TANK_ANIMAL$TANK_ID foreign key (TANK_ID)
    references ZO_TANK (TANK_ID) on delete restrict on update restrict;
alter table ZO_TANK_ANIMAL add constraint FK_ZO_TANK_ANIMAL$ANIMAL_ID foreign key (ANIMAL_ID)
    references ZO_ANIMAL (ANIMAL_ID) on delete restrict on update restrict;


/*==============================================================*/
/* Table: ZO_FOOD_UNIT                                          */
/*==============================================================*/
create table ZO_FOOD_UNIT
(
    FOOD_UNIT_ID         int not null auto_increment  comment '',
    NAME                 varchar(32) not null  comment '',
    SHORT_NAME           varchar(10)  comment '',
    primary key (FOOD_UNIT_ID)
);
alter table ZO_FOOD_UNIT comment 'Единица измерения';


/*==============================================================*/
/* Table: ZO_FOOD                                               */
/*==============================================================*/
create table ZO_FOOD
(
    FOOD_ID              int not null auto_increment  comment '',
    NAME                 varchar(128) not null  comment '',
    FOOD_UNIT_ID         int  comment '',
    primary key (FOOD_ID)
);
alter table ZO_FOOD comment 'Корма для животных';
alter table ZO_FOOD add constraint FK_ZO_FOOD_REFERENCE_ZO_FOOD_ foreign key (FOOD_UNIT_ID)
    references ZO_FOOD_UNIT (FOOD_UNIT_ID) on delete restrict on update restrict;


/*==============================================================*/
/* Table: ZOO_FOOD_ORDER                                        */
/*==============================================================*/
create table ZOO_FOOD_ORDER
(
    FOOD_ORDER_ID        bigint not null auto_increment  comment '',
    ORDER_DT             date not null  comment '',
    STATE                char(1)  comment '',
    ANIMAL_ID            bigint not null  comment '',
    FOOD_ID              int not null  comment '',
    AMOUNT               decimal(10,2) not null  comment '',
    primary key (FOOD_ORDER_ID)
);
alter table ZOO_FOOD_ORDER comment 'Заказ на корм для жмвотного';
alter table ZOO_FOOD_ORDER add constraint FK_ZOO_FOOD_REFERENCE_ZO_ANIMA foreign key (ANIMAL_ID)
    references ZO_ANIMAL (ANIMAL_ID) on delete restrict on update restrict;
alter table ZOO_FOOD_ORDER add constraint FK_ZOO_FOOD_REFERENCE_ZO_FOOD foreign key (FOOD_ID)
    references ZO_FOOD (FOOD_ID) on delete restrict on update restrict;
