# SQL Injection Vulnerability: VulnerableAuthGateway

## Why the concatenation is unsafe

The original code builds the SQL query by directly concatenating raw user
input (`emailInput` and `passwordInput`) into the query string:

    String query = "SELECT * FROM members WHERE email = '" + emailInput +
                    "' AND password = '" + passwordInput + "'";

Because the input is inserted as literal text rather than as a bound
parameter, the database cannot tell the difference between "data" and
"SQL syntax." Anything the user types becomes part of the executable
query. An attacker can close the intended string early with a single
quote and inject their own SQL logic, changing the meaning of the WHERE
clause entirely.

## Example exploit payload

If an attacker enters the following as the `emailInput`:

    ' OR '1'='1' --

The resulting query becomes:

    SELECT * FROM members WHERE email = '' OR '1'='1' --' AND password = '...'

Breaking this down:
- `' OR '1'='1'` turns the WHERE clause into a condition that is always
  true, regardless of the actual email value.
- `--` starts a SQL comment, which causes the database to ignore the
  remainder of the query (including the password check entirely).

Since `1=1` is always true, this query returns every row in the
`members` table, and `rs.next()` returns `true` — meaning the
attacker is authenticated as the first member in the table without
ever supplying a valid password.