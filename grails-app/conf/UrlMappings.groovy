class UrlMappings {
    static mappings = {
      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }

      "/"(view:"/home.gsp")
	  "500"(controller: "error", action: "unknownFail")
	}
}
