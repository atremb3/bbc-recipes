# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table recipe (
  id                        bigint not null,
  name                      varchar(255),
  category                  varchar(255),
  page                      integer,
  issue                     varchar(255),
  nb_time_cooked            varchar(255),
  comment                   varchar(255),
  nb_stars                  integer,
  constraint pk_recipe primary key (id))
;

create table recipe_header (
  name                      varchar(255),
  category                  varchar(255),
  page                      integer,
  issue                     varchar(255))
;

create sequence recipe_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists recipe;

drop table if exists recipe_header;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists recipe_seq;

