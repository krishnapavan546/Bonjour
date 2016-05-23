package com.ysl.bonjour.controller;

/**
 * @author pku134
 * Factory class to get controller object of YSL application.
 *
 */
public class  BonjourControllerFactory {
	
	public static Controller getController(String controllerName)
	{
		if(controllerName==null)
		{
			return null;
		}
		if("UserController".equalsIgnoreCase(controllerName))
		{
			return new UserController();
		}
		if("ServiceController".equalsIgnoreCase(controllerName))
		{
			return new ServiceController();
		}
		if("MakeoverController".equalsIgnoreCase(controllerName))
		{
			return new MakeoverListController();
		}
		if("MakeupDetailedController".equalsIgnoreCase(controllerName))
		{
			return new MakeupDetailedController();
		}
		return null;		
	}

	private BonjourControllerFactory() {
		super();
	}

}
