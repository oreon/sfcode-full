package com.restful.service;

import javax.ws.rs.core.Application;

import com.restful.CustomerAlone;

import java.util.HashSet;
import java.util.Set;

public class RootApplication extends Application
{
   private Set<Object> singletons = new HashSet<Object>();
   private Set<Class<?>> empty = new HashSet<Class<?>>();

   public RootApplication()
   {
      singletons.add(new CustomerAlone());
   }

   @Override
   public Set<Class<?>> getClasses()
   {
      return empty;
   }

   @Override
   public Set<Object> getSingletons()
   {
      return singletons;
   }
}
