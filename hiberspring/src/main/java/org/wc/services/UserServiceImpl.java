package org.wc.services;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.wc.restful.User;

@Service
public class UserServiceImpl implements UserService {
	private final AtomicLong USER_ID_SEQ = new AtomicLong();
	private final ConcurrentMap<Long, User> usersMap = new ConcurrentHashMap<Long, User>();

	@Override
	public User create(User user) {
		user.setId(USER_ID_SEQ.incrementAndGet());
		usersMap.put(user.getId(), user);
		return user;
	}

	@Override
	public User read(long userId) {
		return usersMap.get(userId);
	}

	@Override
	public User update(User user) {
		User updatedUser = usersMap.replace(user.getId(), user);
		Validate.isTrue(updatedUser != null, "Unable to find user with id: "
				+ user.getId());
		return updatedUser;
	}

	@Override
	public User delete(long userId) {
		User removedUser = usersMap.remove(userId);
		Validate.isTrue(removedUser != null, "Unable to find user with id: "
				+ userId);
		return removedUser;
	}

	@Override
	public Collection<User> list() {
		return usersMap.values();
	}
}