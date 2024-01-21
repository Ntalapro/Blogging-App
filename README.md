# Blogging Application
App building is in progress, this is what I have so far.
Open to collaboration. 
## Json Enitites

### User

    {
	    "id":1,
	    "username":"Tshepiso",
	    "email" : "tshepiso@gmail.com",
	    "password" = "***************",
	    "authoToken":"hfdwuigfwihfepoaifhpafijghiwghvisdih"
	    "bio": "I write clean, maintainable code",
	    "image": "https://angular.io/assets/images/logos/angular/logo-nav@2x.png"
    }
### Profile

    {
	    "username":"tshepiso",
	    "bio":"writes great articles!",
	    "image": "https://angular.io/assets/images/logos/angular/logo-nav@2x.png"
	}


### Article

       {
        "id":1,
		"title": "Clean Code",
		"subtitle":"An article about writing clean and maintainable code.",
		"slug":"This is the slug of the article"
		"body" : "This is an article about ... <b>clean code<b/>...<i>2022</i>...",
		"createdAt": "2022-02-06 04:47:38",
		"tags": ["code","maintainability"]
	   }
### Comment

    {
	    "id":1,
	    "title": "great article",
	    "body":"this is a comment on the article",
	    "createdAt":"2022-02-06 04:47:38"
    }


## API Endpoints
### `POST /users`
create new user
### `POST /users/login`
login user
### `Login Error`
```agsl
    {
        "message": "User with username: Tshepiso1 not found "
    }
```
### `GET /profiles/{username}`
get user profile

### `GET /articles`📃
get all articles (default page size 10)
available filters

- `/articles?tag=stoccks`
-  `/articles?author=tshepiso`
-  `/articles?page=3&size=10`
###  `GET /articles/{article-slug}`
### `POST /articles`🔐
create a new article

### `PATCH /articles/{article-slug}`🔐👤
edit an article
### `GET /articles/{article-slug}/comments`📃
get all comments of article
### `POST /articles/{article-slug}/comments`🔐👤
create new comment on article

### `DELETE  /articles/{article-slug}/comments/{comment-id}`🔐👤
