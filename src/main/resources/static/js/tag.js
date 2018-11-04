fetch(`/api/doggos`)
	.then(res => res.json())
	.then(data => {
		const body = document.body
		
		const content = `
			<div class="container">
			<header>
				<h1>Tags</h1>
				<ul>
					<li><a href="../doggos" >Doggos</a></li>
					<li><a href="../categories">Categories</a></li>
				</ul>
			</header>
			<main>
				
			</main>
			<footer>
				<small>&copy; WCCI 2018</small>
				<small><a href="https://omfgdogs.com">if you like dogs</a></small>
			</footer>
			</div>
		`
			
		body.innerHTML = content
		
		const list = document.createElement('ul')
		
		data.forEach(doggo => {
			doggo.tags.forEach(tag => {
				if(tag.tagName===`${window.location.pathname.split('/')[2]}`){
					const doggoLi = document.createElement('li')
					doggoLi.innerHTML = `<a href="/doggos/${doggo.id}">${doggo.title} Boy</a>`
					list.appendChild(doggoLi)
				}
			})
		})
		
		const main = document.querySelector('main')
		main.appendChild(list)
	})