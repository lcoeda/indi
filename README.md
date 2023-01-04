# indi
use case
<img width="80%" src="https://user-images.githubusercontent.com/120078825/209827230-5de8328b-a5a6-45a9-99e0-7f407d1ed4a6.png"/>

|Method|URL|Request|Response|
|------|---|-------|--------|
|@GetMapping|/api/memos|없음|{생성 시간, 수정 시간, "title":"title","author":"author","contents":"contents"}|
|@GetMapping|/api/memos/{id}|없음|{생성 시간, 수정 시간, "title":"title","author":"author","contents":"contents"}|
|@PostMapping|/api/memos|{"title":"title","author":"author","pw":"pw","contents":"contents"}|{생성 시간, 수정 시간, "title":"title","author":"author","contents":"contents"}|
|@PutMapping or @PatchMapping|/api/memos/{id}|{"title":"difftitle","author":"diffauthor","pw":"diffpw","contents":"diffcontents"}|{생성 시간, 수정 시간, "title":"difftitle","author":"diffauthor","contents":"diffcontents"}|
|@DeleteMapping|/api/memos/{id}|{"id":"id","pw":"pw"}|"success":"삭제되었습니다."|
![스크린샷(23)](https://user-images.githubusercontent.com/120078825/210574139-20b09838-787f-48b6-9d8d-ac4e0a1ab4d2.png)
