package com.toda.service;

import com.toda.Exception.ResourceNotFoundException;
import com.toda.dao.ItemDetailsRepository;
import com.toda.dao.ItemRepository;
import com.toda.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Optional;

@Service
public class ItemService {

    @Autowired
   private RestTemplate restTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemDetailsRepository itemDetailsRepository;



    public Item addItem(Item item) {


        return itemRepository.save(item );
    }

    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }

    public Item updateItem(int id, Item updatedItem) throws ResourceNotFoundException {

        Optional<Item> optionalItem=itemRepository.findById(id);
            if(optionalItem.isPresent()){

                Item item= optionalItem.get();
                item.setTitle(updatedItem.getTitle());
                item.setItemDetails(updatedItem.getItemDetails());
                return itemRepository.save(item);
            }else{
                throw new ResourceNotFoundException("Item not found with id " + id);
            }
     }



    public  Item searchItem(int id) throws ResourceNotFoundException {
        if(id<= 0){
            throw new ResourceNotFoundException("item not found with id + "+id);
        }else
        {
              Item  item=itemRepository.findItemById(id);

        return item;
        }


    }


    public Optional<Item> searchItemById(int id) {

       Optional<Item> item=  itemRepository.findById(id);
       return item;
    }
}
