package com.algaworks.patrimonio.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.patrimonio.model.Item;
import com.algaworks.patrimonio.repository.ItemRepository;

@RestController
@RequestMapping("/itens")
@CrossOrigin("${origem-permitida}")
public class ItemResource {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@GetMapping
	public List<Item> listar() {
		return itemRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Item> buscar(@PathVariable Long id) {
		Item item = itemRepository.findOne(id);
		
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(item);
	}
	
	@PostMapping
	public ResponseEntity<Item> adicionar(@RequestBody @Valid Item item) {
		Item itemAdicionado = itemRepository.save(item);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(itemAdicionado.getId()).toUri();
		
		return ResponseEntity.created(uri).body(itemAdicionado);
	}
	
	@PutMapping("/{id}")
	public Item alterar(@PathVariable Long id, @RequestBody @Valid Item item) {
		Item itemExistente = itemRepository.findOne(id);
		
		BeanUtils.copyProperties(item, itemExistente, "id");
		
		return itemRepository.save(itemExistente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Item> deletar(@PathVariable Long id) {
		Item item = itemRepository.findOne(id);
		
		if (item == null) {
			return ResponseEntity.notFound().build();
		}
		
		itemRepository.delete(item);
		
		return ResponseEntity.noContent().build();
	}
}
