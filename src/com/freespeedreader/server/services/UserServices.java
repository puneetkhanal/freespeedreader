package com.freespeedreader.server.services;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class UserServices {
	UserService userService = UserServiceFactory.getUserService();
}
