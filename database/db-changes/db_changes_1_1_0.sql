-----------------------------------------------------
Name- Yogesh
Date- 22-October-2020
Additon of columns
-----------------------------------------------------
ALTER TABLE `app_config`.`app_config` 
ADD COLUMN `device_type` VARCHAR(45) NULL;

ALTER TABLE `app_config`.`app_user_field_map` 
ADD COLUMN `group_sort_order` INT NULL ,
ADD COLUMN `field_sort_order` INT NULL ;

ALTER TABLE `app_config`.`app_user_field_map` 
ADD COLUMN `status` INT(2) NULL ;

ALTER TABLE `app_config`.`app_product_service` 
CHANGE COLUMN `product_service_id` `product_offering_service_id` INT(11) NULL DEFAULT NULL , RENAME TO  `app_config`.`app_product_offering_service` ;