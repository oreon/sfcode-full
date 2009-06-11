using System;
using System.Collections.Generic;
//using System.Linq;
using System.Text;
using FirstHiber.domain;
using NHibernate.Cfg;
using NHibernate.Tool.hbm2ddl;

using NUnit.Framework;

namespace FirstHiber.Tests
{
    [TestFixture]
    public class GenerateSchema_Fixture
    {

        [Test]
        public void Can_generate_schema()
        {

            var cfg = new Configuration();

            cfg.Configure();

            cfg.AddAssembly(typeof(Drug).Assembly);



            new SchemaExport(cfg).Execute(false, true, false, false);

        }

        public static void Main()
        {
            var cfg = new Configuration();

            cfg.Configure();

            cfg.AddAssembly(typeof(Product).Assembly);

            new SchemaExport(cfg).Execute(false, true, false, false);

        }

    }
}




