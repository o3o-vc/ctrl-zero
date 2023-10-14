listByUserId
===
```sql
select
    m.*
from menu m
left join role_menu_mapping rm on m.id = rm.menu_id
left join role r on r.id = rm.role_id
left join role_user_mapping ru on ru.role_id = r.id
where ru.user_id = #{userId}
group by m.id

```