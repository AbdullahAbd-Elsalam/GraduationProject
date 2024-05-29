package com.toda.controller;

import com.toda.Exception.NotValidTokenException;
import com.toda.Exception.UnauthorizedException;
import com.toda.dao.ItemDetailsRepository;
import com.toda.model.Item;
import com.toda.model.Item_Details;
import com.toda.service.ItemService;
import com.toda.service.JwtService;
import com.toda.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@Api(tags = "this documentation for manage  item for user account ")
public class ItemController {
    //@RequestMapping("/api/v1/item")
    @Autowired
    private ItemService itemService;

   private ItemDetailsRepository itemDetailsRepository;


    @Autowired
    private TokenService tokenService;

    @Autowired
   private JwtService jwtService;

    @GetMapping("/protected-resource")
    @ApiOperation(value = "this operation  to make user to delete account  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "successful request "),
            @ApiResponse(code = 404,message = "error for client"),
            @ApiResponse(code = 500,message = "error for server")
    }
    )
    public String getProtectedResource(@RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7); // Remove "Bearer " prefix
        if (tokenService.isTokenValid(jwtToken)) {
            return "Access granted to protected resource.";
        } else {
            return "Invalid token.";
        }
    }

    @GetMapping("/test")
    public String testmethod(){
        return  "hi";
    }

    @PostMapping("/item")
    @ApiOperation(value = "this operation  to make user to delete account  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "successful request "),
            @ApiResponse(code = 404,message = "error for client"),
            @ApiResponse(code = 500,message = "error for server")
    }
    )
    public ResponseEntity<Item> addItem(@RequestBody Item_Details itemDetails, @RequestHeader("Authorization") String token) {
        try {
            String jwtToken = token.substring(7); // Remove "Bearer " prefix
            if (tokenService.isTokenValid(jwtToken)) {
                String userId = jwtService.getExtractUserEmail(jwtToken);
                Item item = new Item();
                item.setTitle(itemDetails.getDescription());
                item.setUserId(userId);
                item.setItemDetails(itemDetails);
                Item savedItem = itemService.addItem(item);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
            } else {
                throw new UnauthorizedException("Invalid token");
            }
        } catch (UnauthorizedException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @DeleteMapping("/item/{id}")
    @ApiOperation(value = "this operation  to make user to delete account  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "successful request "),
            @ApiResponse(code = 404,message = "error for client"),
            @ApiResponse(code = 500,message = "error for server")
    }
    )
    public ResponseEntity<String> deleteItem(@PathVariable("id") int itemId, @RequestHeader("Authorization") String token) {
        try {
            String jwtToken = token.substring(7); // Remove "Bearer " prefix
            if (tokenService.isTokenValid(jwtToken)) {
                itemService.deleteItem(itemId);
                return ResponseEntity.ok("Item deleted successfully");
            } else {
                throw new UnauthorizedException("Invalid token");
            }
        } catch (UnauthorizedException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the item");
        }
    }

    @GetMapping("/search/{id}")
    @ApiOperation(value = "this operation  to make user to delete account  ")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "successful request "),
            @ApiResponse(code = 404,message = "error for client"),
            @ApiResponse(code = 500,message = "error for server")
    }
    )
    public ResponseEntity<Item> getItem(@PathVariable int id, @RequestHeader("Authorization") String token) {
        try {
            String jwtToken = token.substring(7); // Remove "Bearer " prefix
            if (tokenService.isTokenValid(jwtToken)) {
                Item item = itemService.searchItem(id);
                return ResponseEntity.ok(item);
            } else {
                throw new UnauthorizedException("Invalid token");
            }
        } catch (UnauthorizedException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
          catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody Item item, @RequestHeader("Authorization") String token) throws ResourceNotFoundException, UnauthorizedException {
//        if (userService.checkTokenValidity(token)) {
//            return new ResponseEntity<>(itemService.updateItem(id, item), HttpStatus.OK);
//        } else {
//            throw new UnauthorizedException("Invalid token");
//        }
//    }
//





}
