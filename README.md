# indi
use case
<img width="80%" src="https://user-images.githubusercontent.com/120078825/209827230-5de8328b-a5a6-45a9-99e0-7f407d1ed4a6.png"/>

로그인 시 Header에 토큰 bear~~~ 생성
토큰이 유효할 경우에 삭제와 수정 가능

|Inform|Method|URL|Request|Response|
|-------|------|-------|-------|--------|
|전체게시판 게시글조회|@GetMapping|/posts|없음|{생성 시간, 수정 시간, "title":"title","username":"username","contents":"contents"}|
|원하는 게시글조회|@GetMapping|/posts/{id}|없음|{생성 시간, 수정 시간, "title":"title","username":"username","contents":"contents"}|
|-------|------|-------|-------|--------|
|게시글 저장|@PostMapping|/posts|{"title":"title","username":"username","contents":"contents"}|{생성 시간, 수정 시간, "title":"title","author":"author","contents":"contents"}|
|회원가입|@PostMapping|/signup|{"username":"username", "pw":"pw", "email":"email", "phone":"phone"}|{"success":"가입 성공"}|
|로그인|@PostMapping|/login|"username":"username", "pw":"pw"|"success":"로그인 성공"|
|-------|------|-------|-------|--------|
|내 게시글 수정|@PutMapping or @PatchMapping|/posts/{id}|{"title":"difftitle","author":"diffauthor","pw":"diffpw","contents":"diffcontents"}|{생성 시간, 수정 시간, "title":"difftitle","author":"diffauthor","contents":"diffcontents"}|
|내 게시글 삭제|@DeleteMapping|/posts/{id}|{"id":"id","pw":"pw"}|"success":"삭제되었습니다."|


![스크린샷(23)](https://user-images.githubusercontent.com/120078825/210574139-20b09838-787f-48b6-9d8d-ac4e0a1ab4d2.png)
