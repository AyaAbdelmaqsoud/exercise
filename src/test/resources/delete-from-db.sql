--- DROP ALL OBJECTS DELETE FILES;

----------------------------------------------------------------------------------
-- there is currently an error with H2 to rebuild the database schema in every junit method
-- we keep the schema during one junit test but delete the content and insert it in every JUnit method.
----------------------------------------------------------------------------------

DELETE from customer;







