package com.beertag.demo.constants;

public class SQLQueryConstants {
    public static final String RATE_BEER_SQL = "insert into rating (rating, drank_id) " +
            "select %d, drank_beer_id " +
            "from drank_beer " +
            "where username = '%s' and beer_id = '%d';";
    public static final String GET_ENABLE_USER_SQL = "from User where username = :name and enabled = :status ";
    public static final String INSERT_USER_ROLE_SQL = "insert into authorities " +
            "value ('%s','ROLE_USER')";
    public static final String GET_WISHLIST_SQL = "select * " +
            "from beer join wish_beer " +
            "on beer.beer_id = wish_beer.beer_id " +
            "join users on wish_beer.username = users.username " +
            "where users.username = :username and wish_beer.status = :status";
    public static final String IS_RATING_EXITS_SQL = "select * " +
            "from rating " +
            "where drank_id = (select (drank_beer_id)" +
            "    from drank_beer " +
            "    where username = :username and beer_id = :beerId and status = :status);" ;

    public static final String GET_RATING_FROM_BEER_SQL = "select * from rating" +
            " join drank_beer on rating.drank_id = drank_beer.drank_beer_id" +
            " where beer_id = :beerId " +
            "and drank_beer.status = :beerStatus";
    public static final String GET_DRANKLIST_SQL = "select * " +
            "    from beer " +
            "    join drank_beer db on beer.beer_id = db.beer_id " +
            "    join users u on db.username = u.username " +
            "    where u.username = :username and db.status = :status ;";

    //Status
    public static final int ENABLE = 1;
    public static final int DISABLE = 0;

}
