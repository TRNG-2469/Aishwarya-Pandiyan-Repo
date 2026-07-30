The 4 JDBC Driver Types:

Type 1 - JDBC-ODBC Bridge
Translates JDBC calls into ODBC calls, which then talk to the database. Requires ODBC drivers installed on the client machine. Mostly obsolete now, removed from Java itself since Java 8. Slow and platform-dependent.

Type 2 - Native-API Driver
Converts JDBC calls into native calls of the database's own client-side API (e.g. Oracle OCI). Needs vendor-specific native libraries installed on the client. Faster than Type 1, but still platform-dependent since it relies on native binaries.

Type 3 - Network Protocol Driver
Sends JDBC calls to a middleware server, which translates them into the database-specific protocol. The client itself doesn't need vendor-specific software, the middleware handles that. Flexible and platform-independent, but adds an extra network hop through the middleware.

Type 4 - Thin Driver (Pure Java Driver)
Directly converts JDBC calls into the database's own network protocol, written entirely in Java. No native code, no middleware, just talks straight to the database over the network. This is what almost everyone uses today (e.g. MySQL Connector/J, PostgreSQL JDBC driver). Platform-independent and fastest of the four.