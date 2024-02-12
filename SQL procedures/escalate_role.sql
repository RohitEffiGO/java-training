CREATE OR REPLACE PROCEDURE escalate_role(IN email_input VARCHAR) AS
$$
	DECLARE
		role_id BIGINT;
	  	email_id BIGINT;
		cust_role_user_id BIGINT;
		role_type VARCHAR;
	BEGIN
		email_id := (SELECT cu.id FROM cust_user cu WHERE cu.email = email_input);
		role_id := (SELECT cust_role.id FROM cust_role WHERE cust_role.role_type = 'LEARNER');
		cust_role_user_id := (SELECT cust_user_role.cust_role_id FROM cust_user_role WHERE cust_user_role.cust_user_id = email_id);
		role_type := (SELECT cust_role.role_type FROM cust_role WHERE cust_role.id = cust_role_user_id);
		
		if role_type = 'LEARNER' THEN
			role_id := (SELECT cust_role.id FROM cust_role WHERE cust_role.role_type = 'AUTHOR');
			cust_role_user_id := role_id;
			
		elsif role_type = 'AUTHOR' THEN
			role_id := (SELECT cust_role.id FROM cust_role WHERE cust_role.role_type = 'ADMIN');
			cust_role_user_id := role_id;
		end if;		
		UPDATE cust_user_role SET cust_role_id = cust_role_user_id WHERE cust_user_id = email_id;
		
	END;
$$ language 'plpgsql';