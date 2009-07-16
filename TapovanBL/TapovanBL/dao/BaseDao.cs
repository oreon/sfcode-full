using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using NHibernate.Cfg;

using NHibernate.Tool.hbm2ddl;
using NHibernate;
using NHibernate.Criterion;
using TapovanBL.domain;

namespace TapovanBL.dao
{
    public abstract class BaseDao<T> 
    {
        Configuration cfg = new Configuration();
        ISessionFactory factory; 

        public BaseDao()
        {
            init();
        }

        public void init()
        {
            cfg.Configure();
            //new SchemaExport(cfg).Execute(true, true, false, true);
            factory =  cfg.BuildSessionFactory();
         }

        public ITransaction beginTransaction()
        {
            ISession session = factory.OpenSession();
            ITransaction transaction = session.BeginTransaction();
            return transaction;
        }

        public void save(T t)
        {
            ISession session = factory.OpenSession();
            ITransaction transaction = session.BeginTransaction();

            session.SaveOrUpdate(t);
            
            transaction.Commit();
            session.Close();
        }

        public void delete(T t)
        {
            ISession session = factory.OpenSession();
            ITransaction transaction = session.BeginTransaction();

            session.Delete(t);

            transaction.Commit();
            session.Close();
        }

        public T load(long id)
        {
            ISession session = factory.OpenSession();
            //ITransaction transaction = session.BeginTransaction();
           Type mytype = getType();
            T t =  (T)session.Load( mytype, id) ;
            
            session.Close();
            return t;
            //transaction.Commit();
        }

        public List<T> loadAll()
        {
            List<T> list = new List<T>();
            ISession session = factory.OpenSession();
            
            //ITransaction transaction = session.BeginTransaction();
            IQuery query = session.CreateQuery("select from " + getType().Name); //t.GetType().Name);
            query.List(list);
            session.Close();
            return list;
        }

        protected Type getType()
        {
            Type tgen = typeof(SimpleGeneric<T>);
            Type gen = tgen.GetGenericArguments()[0];
            return gen;
        }
        
        public List<T> findByExample(T t)
        {
            List<T> list = new List<T>();
            ISession session = factory.OpenSession();
            list = (List<T>)session.CreateCriteria(typeof(T)).Add(Example.Create(t)).List<T>() ;
           // IQuery query = session.CreateQuery("select from " + " Student "); //t.GetType().Name);
           // query.List(list);
            session.Close();
            return list;

        }
    }

    //used to get the type 
     class SimpleGeneric<T>
    {
        public SimpleGeneric()
        {
        }
    }
}
