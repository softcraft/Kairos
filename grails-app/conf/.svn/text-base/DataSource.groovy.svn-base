dataSource {
	pooled = false
}
// environment specific settings

hibernate {
	cache.use_second_level_cache=true
	cache.use_query_cache=true
	cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}

environments {
	development {
		dataSource {
				dbCreate = "create-drop" // one of 'create', 'create-drop','update'
				url = "jdbc:hsqldb:mem:devDB"
			}
	}
	test {
		dataSource {
			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
				url = "jdbc:hsqldb:mem:testDB"
		}
	}
	production {
			dataSource {
				dbCreate = "update"
				driverClassName = "com.mysql.jdbc.Driver"
				url = "jdbc:mysql://localhost:3306/k4iros"
				username = "root"
				password = "}]qE[=IB]dUU]npnKwAtI&o|Fq!K5s"
			}
		}
	}