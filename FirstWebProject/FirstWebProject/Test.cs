using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NHibernate;

namespace FirstWebProject
{
    public class Test
    {
        public static void Main()
        {
            ISession session = NHibernateHelper.GetCurrentSession();

            ITransaction tx = session.BeginTransaction();

            Cat princess = new Cat();
            princess.Name = "Princess";
            princess.Sex = 'F';
            princess.Weight = 7.4f;

            session.Save(princess);
            tx.Commit();

            NHibernateHelper.CloseSession();

        }
    }
}
