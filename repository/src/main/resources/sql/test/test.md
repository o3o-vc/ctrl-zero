select
===
```sql
    select * from test
```

page
===
```sql
select 
    #{page('*')} 
from test 
where 1 = 1
-- @ if(!isBlank(name)) {
    and name = #{name}
-- @ }
-- @ if(!isBlank(mark)) {
    and mark like #{'%'+ mark +'%'}
-- @ }
```

testInsert
===
```sql
insert into test (name, mark) values (#{name}, #{mark})
```


