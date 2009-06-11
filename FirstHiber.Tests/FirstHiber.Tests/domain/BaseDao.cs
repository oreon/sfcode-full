using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using NHibernate.Cfg;
using FirstHiber.domain;
using NHibernate.Tool.hbm2ddl;
using NHibernate;


namespace FirstHiber.domain
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
            //cfg.AddAssembly(typeof(Drug).Assembly);
            //new SchemaExport(cfg).Execute(false, true, false, false);
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

            session.Save(t);
            
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

        public T load(long id, T t)
        {
            ISession session = factory.OpenSession();
            //ITransaction transaction = session.BeginTransaction();
            session.Load(t, id);
            session.Close();
            return t;
            //transaction.Commit();
        }
    }
}
