package org.wc.services;

import java.util.Collection;

import org.wc.restful.User;

public interface UserService {
	User create(User user);

	User read(long userId);

	User update(User user);

	User delete(long userId);

	Collection<User> list();
}