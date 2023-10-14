select
===
```sql
    select * from test
```

page
===
```sql
select 
    #{page("*")} 
from test 
where 1 = 1
-- @ if(!isBlank(name)) {
    and name = #{name}
-- @ }
```

testInsert
===
```sql
insert into test (name, mark) values (#{testDo.name}, #{testDo.mark})
```
