CREATE TABLE save_customer
(
    username      varchar2(100) not null,
    date_of_birth varchar2(100) not null
);

CREATE OR REPLACE PACKAGE GBEMISOLA.expand_world_pkg AS

    PROCEDURE save_user (
        out_response_code OUT  VARCHAR2,
        out_response_message     OUT  VARCHAR2,
        p_username  IN  VARCHAR2,
        p_date_of_birth    IN  VARCHAR2
    );

    PROCEDURE update_user (
        out_response_code OUT VARCHAR2,
        out_response_message OUT VARCHAR2,
        p_username IN VARCHAR2,
        p_date_of_birth IN VARCHAR2

    );


    PROCEDURE get_by_user_id (
        out_response_code        OUT  VARCHAR2,
        out_response_message     OUT  VARCHAR2,
        quote_data         OUT  SYS_REFCURSOR,
        p_username  IN   VARCHAR2
    );

END expand_world_pkg;

CREATE OR REPLACE PACKAGE BODY GBEMISOLA.expand_world_pkg AS

    /*****************************************************************************/
/************************save user***********************/
/****************************************************************************/
/*****************************************************************************/
    PROCEDURE save_user (
        out_response_code OUT  VARCHAR2,
        out_response_message     OUT  VARCHAR2,
        p_username  IN  VARCHAR2,
        p_date_of_birth    IN  VARCHAR2
    ) AS

    BEGIN
        INSERT INTO USERNAME (
            username,
            date_of_birth


        ) VALUES (
                     p_username,
                     p_date_of_birth
                 );
        COMMIT;
        out_response_code := '000';
        out_response_message := 'SUCCESS';

    EXCEPTION
        WHEN OTHERS THEN
            out_response_code := sqlcode;
            out_response_message := dbms_utility.format_error_backtrace || sqlerrm;
    END save_user;


    /*****************************************************************************/
/************************update user***********************/
/****************************************************************************/
/*****************************************************************************/

    PROCEDURE update_user (
        out_response_code OUT VARCHAR2,
        out_response_message OUT VARCHAR2,
        p_username  IN  VARCHAR2,
        p_date_of_birth IN  VARCHAR2
    )
        IS
    BEGIN
        UPDATE
            USERNAME
        SET
            username = p_username,
            date_of_birth = p_date_of_birth
        WHERE
                username = p_username;

        COMMIT;
        out_response_code := '000';
        out_response_message := 'SUCCESS';


    EXCEPTION
        WHEN OTHERS THEN
            out_response_code := sqlcode;
            out_response_message := dbms_utility.format_error_backtrace || sqlerrm;


    END update_user;


/*****************************************************************************/
/************************get user by username***********************/
/****************************************************************************/
/*****************************************************************************/

    PROCEDURE get_by_user_id (
        out_response_code OUT VARCHAR2,
        out_response_message OUT VARCHAR2,
        quote_data OUT SYS_REFCURSOR,
        p_username IN VARCHAR2
    ) AS
        count_row NUMBER;
    BEGIN
        SELECT
            COUNT(*)
        INTO
            count_row
        FROM
            USERNAME a
        WHERE
                a.username = p_username;

        IF ( count_row > 0 ) THEN
            OPEN quote_data FOR
                SELECT
                    *
                FROM
                    USERNAME a
                WHERE
                        a.username = p_username;

            out_response_code := '000';

            out_response_message := 'SUCCESS';
        ELSE
            out_response_code := '99';

            out_response_message := 'No Record Found';
        END IF;

    EXCEPTION
        WHEN OTHERS THEN
            out_response_code := sqlcode;

            out_response_message := dbms_utility.format_error_backtrace || sqlerrm;
    END get_by_user_id;

END expand_world_pkg;