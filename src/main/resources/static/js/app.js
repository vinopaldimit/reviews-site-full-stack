const input = document.querySelector('#tagInput')
const addTagButton = document.querySelector('button')

addTagButton.addEventListener('click', () => {
	fetch(`/api/doggos/${window.location.pathname.split('/')[2]}/tags/add`, {
		method:'post',
		body: JSON.stringify({
			tagName: input.value,
		}),
	})
		.then(res => res.json())
		.then(data => console.log)
})

const tagDisplay = document.querySelector('.tagDisplay')

fetch(`/api/doggos/${window.location.pathname.split('/')[2]}`)
	.then(res => res.json())
	.then(data => {
		const tags = document.createElement('p')

		tags.innerText = 'Tags: '
		data.tags.forEach(tag => {
			tags.innerText += tag.tagName + ' ';
		})
		
		tagDisplay.appendChild(tags)
	})
	
fetch(`/api/tags/${window.location.pathname.split('/')[2]}`)
	.then(res => res.json())
	.then(data => {
		
	})