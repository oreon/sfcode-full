using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NHibernate.Cfg;
using FirstHiber.domain;
using NHibernate.Tool.hbm2ddl;
using NHibernate;

namespace FirstHiber
{
    public class Class1
    {
        public static void Main()
        {
            Drug drug = new Drug();
            drug.Name = ("Citrazine");
            DrugDao dao = new DrugDao();
            dao.save(drug);

            /*
            var cfg = new Configuration();
            cfg.Configure();
            cfg.AddAssembly(typeof(Drug).Assembly);
            new SchemaExport(cfg).Execute(false, true, false, false);

            ISessionFactory factory = cfg.BuildSessionFactory();
            ISession session = factory.OpenSession();
            ITransaction transaction = session.BeginTransaction();

            Drug drug = new Drug();
            drug.Name = ("Lipitor");
           // Product product = new Product { Name = "Apple", Category = "Fruits" };

            session.Save(drug);
            transaction.Commit();
            session.Close(); */

        }
    }
}
