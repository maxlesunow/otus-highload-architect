package ru.mlesunov.otus.cache.feed;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.mlesunov.otus.entity.UserFeed;

@Service
@RequiredArgsConstructor
public class FeedCachedService {

    private final String EMPLOYEE_CACHE = "EMPLOYEE";

    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, Employee> hashOperations;

    private final RedisTemplate<String, List<>> redisTemplate;

    @Autowired
    public RedisTemplateService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // This annotation makes sure that the method needs to be executed after
    // dependency injection is done to perform any initialization.
    @PostConstruct
    private void intializeHashOperations() {
        hashOperations = redisTemplate.opsForHash();
    }

    // Save operation.
    @Override
    public void save(final Employee employee) {
        hashOperations.put(EMPLOYEE_CACHE, employee.getId(), employee);
    }

    // Find by employee id operation.
    @Override
    public Employee findById(final String id) {
        return (Employee) hashOperations.get(EMPLOYEE_CACHE, id);
    }
    private final RedisTemplate redisTemplate;
    public void setUserFeed(UserFeed feed){
        redisTemplate.
    }

    public UserFeed getUserFeedByUserId(String userId){

    }
}
